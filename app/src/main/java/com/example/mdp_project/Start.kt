package com.example.mdp_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)

//        val splashGif = findViewById<ImageView>(R.id.splashGif)
//        Glide.with(this).asGif().load(R.drawable.openingbackground).into(splashGif)

        GlobalScope.launch(Dispatchers.IO) {
            val User = Utils.getFromSql("userlist", "user")
            val Pass = Utils.getFromSql("userlist", "pass")
            val deviceId = Utils.getFromSql("devicelist", "deviceid")
            val deviceName = Utils.getFromSql("devicelist", "devicename")
            val deviceType = Utils.getFromSql("devicelist", "devicetype")
            val entories2 = Utils.getFromSqlFloat("mdptest1", "value", "WHERE type = 'humidity' ORDER BY date DESC LIMIT 20")
            val entoriesD = Utils.getFromSqlDate("mdptest1", "date", "WHERE type = 'temperature' ORDER BY date DESC LIMIT 10")
            val entories1 = Utils.getFromSqlFloat("mdptest1", "value", "WHERE type = 'temperature' ORDER BY date DESC LIMIT 20")
            withContext(Dispatchers.Main) {
                for(i in User.indices){
                    MockDB.user.add(UserList(User[i], Pass[i]))
                }
                for(i in deviceId.indices){
                    MockDB.member.add(MemberList(deviceId[i].toString(), deviceName[i].toString(), deviceType[i].toString()))
                }
                for(i in entoriesD.indices){
                    MockDB.humidity.add(entories2[i])
                    MockDB.temperature.add(entories1[i])
                    MockDB.date.add(entoriesD[i])
                }
//                Log.d("User", MockDB.user.toString())


            }
        }
        Handler().postDelayed({
            val intent = Intent(this@Start, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },2500)
    }
}