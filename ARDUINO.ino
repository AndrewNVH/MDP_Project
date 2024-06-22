#include <LiquidCrystal_I2C.h>
#include <DHT.h>
#include <WiFi.h>
#include <WiFiClient.h>
#include <WebServer.h>
#include <Wire.h>
#include <MySQL_Generic.h>
#include <NTPClient.h>
#include <WiFiUdp.h>

// DHT
#define DHTPIN  4 
#define DHTTYPE DHT11 
DHT dht(DHTPIN, DHTTYPE);

// LCD
#define colum 16
#define row 2
LiquidCrystal_I2C lcd(0x27, colum, row); 

// Variable
int deviceId = 1;
unsigned long Pmillis = 0;

// Wifi
const char* ssid = " TMG"; 
const char* pass = "11335577"; 

// ESP IP
IPAddress staticIP(192, 168, 1, 139); 
IPAddress gateway(192, 168, 1, 1); 
IPAddress subnet(255, 255, 255, 0);
IPAddress dns(1, 1, 1, 1);

// Database
char serverdb[] = "192.168.1.34"; 
uint16_t server_port = 3306;
char user[] = "trial2";                 
char password[] = "trial";           
char database[] = "exampledb";
char table[] = "mdptest1";
MySQL_Connection conn((Client *)&client);

// NTPClient
String weekDays[7] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
String months[12] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP, "pool.ntp.org", (7 * 3600), 60000);

// Webserver
WebServer server(80);

void setup(){

  Initial
  dht.begin();
  Wire.begin();
  lcd.init();
  lcd.backlight();
  pinMode(14, OUTPUT);
  Serial.begin(115200);

  // WIFI
  Serial.print("Connecting");
  WiFi.begin(ssid, pass);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  WiFi.config(staticIP, gateway, subnet, dns);
  if (!WiFi.config(staticIP, gateway, subnet)) {
    Serial.println("Failed to configure Static IP");
  } else {
    Serial.println("Static IP is configured");
  }
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  // NTP
  timeClient.begin();
  timeClient.update();
  Serial.println(getTime());

  // Webserver
  server.on("/LED", []() {
    if (server.hasArg("brightness")) {
      String value = server.arg("brightness");
      int PWM = value.toInt();
      analogWrite(14, map(PWM, 0, 100, 0, 255));
      Serial.println(value);
      server.send(200, "text/json", "{\"message\":\"success\", \"brightness\":\"" + value + "\"}");
    } else {
      server.send(200, "text/json", "{\"message\":\"error\", \"body\":\"Brightness argument not provided\"}");
    }
  });

  server.on("/LCD", []() {
    if (server.hasArg("LCDmessage")) {
      String display = server.arg("LCDmessage");
      lcd.clear();
      lcd.setCursor(0, 0);
      lcd.print(display);
      server.send(200, "text/json", "{\"message\":\"TEXT sent!\"}");
    } else {
      server.send(200, "text/json", "{\"message\":\"TEXT not sent!\"}");
    }
  });
  server.begin();

  // Database
  Serial.println("Connecting to database...");
  if (conn.connect(serverdb, server_port, user, password)) {
    Serial.println("Connected to database");
    conn.close();
  } else {
    Serial.println("Connection to database failed.");
  }

  // doJob before waiting
  timeClient.update();
  doJob();
}

void loop(){
  timeClient.update();
  server.handleClient();
  if (millis() - Pmillis >= 300000) {
    doJob();
    Pmillis = millis();
  }
}

void doJob(){
  String timeStr = getTime();
  Serial.println(timeStr);

  const char* type = "humidity";
  const char* type2 = "temperature";
  float humidity = 0;
  float temperature = 0;
  float humidity = dht.readHumidity();
  float temperature = dht.readTemperature();
 
  // Temperature
  char query[256];
  sprintf(query, "INSERT INTO %s.%s VALUES ('%s', %d, '%s', '%.2f');", database, table, timeStr.c_str(), deviceId, type2, temperature);
  Serial.println(query);

  if (conn.connect(serverdb, server_port, user, password)) {
    Serial.println("Connected to database");
    // Execute the query
    MySQL_Query myQuery(&conn);
    (myQuery.execute(query))?Serial.println("Data temperature inserted."):Serial.println("Insert temperature error");
    conn.close(); // Close the connection
  } else Serial.println("Connection to database failed.");
  

  // Humidity
  sprintf(query, "INSERT INTO %s.%s VALUES ('%s', %d, '%s', '%.2f');", database, table, timeStr.c_str(), deviceId, type, humidity);
  Serial.println(query);

  if (conn.connect(serverdb, server_port, user, password)) {
    Serial.println("Connected to database");
    // Execute the query
    MySQL_Query myQuery(&conn);
    (myQuery.execute(query))?Serial.println("Data humidity inserted."):Serial.println("Insert humidity error");
    conn.close(); // Close the connection
  } else Serial.println("Connection to database failed.");
}

String getTime() {
  // Serial.print("Free Heap: ");
  // Serial.println(ESP.getFreeHeap());
  time_t epochTime = timeClient.getEpochTime();
  // Serial.print("Epoch Time: ");
  // Serial.println(epochTime);

  String formattedTime = timeClient.getFormattedTime();
  // Serial.print("Formatted Time: ");
  // Serial.println(formattedTime);

  int currentHour = timeClient.getHours();
  // Serial.print("Hour: ");
  // Serial.println(currentHour);

  int currentMinute = timeClient.getMinutes();
  // Serial.print("Minutes: ");
  // Serial.println(currentMinute);

  int currentSecond = timeClient.getSeconds();
  // Serial.print("Seconds: ");
  // Serial.println(currentSecond);

  String weekDay = weekDays[timeClient.getDay()];
  // Serial.print("Week Day: ");
  // Serial.println(weekDay);

  // Get a time structure
  struct tm *ptm = gmtime((time_t *)&epochTime);

  int monthDay = ptm->tm_mday;
  // Serial.print("Month day: ");
  // Serial.println(monthDay);

  int currentMonth = ptm->tm_mon + 1;
  // Serial.print("Month: ");
  // Serial.println(currentMonth);

  String currentMonthName = months[currentMonth - 1];
  // Serial.print("Month name: ");
  // Serial.println(currentMonthName);

  int currentYear = ptm->tm_year + 1900;
  // Serial.print("Year: ");
  // Serial.println(currentYear);

  // Print complete date:
  String currentDate = String(currentYear) + "-" + String(currentMonth) + "-" + String(monthDay);
  // Serial.print("Current date: ");
  // Serial.println(currentDate);

  // Serial.println("");
  String timeStr = currentDate + " " + formattedTime;
  Serial.println(timeStr);
  return timeStr;
}



