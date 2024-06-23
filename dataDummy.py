import mysql.connector
import pandas as pd
from datetime import datetime, timedelta
import random

# Database configuration
db_config = {
    'user': 'trial2',
    'password': 'trial',
    'host': 'localhost',  # Change to your MariaDB server address if it's not local
    'database': 'exampledb',
}

# Connect to the database
conn = mysql.connector.connect(**db_config)
cursor = conn.cursor()

# Table and column configuration
table_name = 'mdptest1'  # Change to your actual table name

# Generate 5-minute intervals
start_date = datetime(2024, 1, 1, 0, 0)
end_date = datetime.now()
interval = timedelta(minutes=5)

# Prepare the insert query
query = f"""
INSERT INTO {table_name} (date, deviceid, type, value)
VALUES (%s, %s, %s, %s)
"""

# Insert dummy data
current_date = start_date
while current_date <= end_date:
    date_str = current_date.strftime('%Y-%m-%d %H:%M:%S')
    device_id = 1  # Example device ID
    data_type = 'temperature'  # Example type
    value = round(random.uniform(20.0, 30.0), 2)  # Generate random humidity value

    data = (date_str, device_id, data_type, value)
    cursor.execute(query, data)

    current_date += interval

# Commit the transaction
conn.commit()

# Close the connection
cursor.close()
conn.close()

print(f"Data inserted successfully from {start_date.strftime('%Y-%m-%d %H:%M:%S')} to {end_date.strftime('%Y-%m-%d %H:%M:%S')}.")
