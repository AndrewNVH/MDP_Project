package com.example.mdp_project

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.util.Locale

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize the database connection and take usernames and passwords from the database
        GlobalScope.launch(Dispatchers.IO) {
            val User = Utils.getFromSql("userlist", "user")
            val Pass = Utils.getFromSql("userlist", "pass")
            withContext(Dispatchers.Main) {
                for(i in User.indices){
                    MockDB.user.add(UserList(User[i].toString(), Pass[i].toString()))
                }

            }
        }

    }
    private val languageChangeReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?
        ) {
            intent?.let {
                if (it.action == "com.example.ACTION_LANGUAGE_CHANGED") {
                    val languageCode = it.getStringExtra("languageCode")
                    val locale = Locale(languageCode!!)
                    Locale.setDefault(locale)
                    val resources = resources
                    val configuration = resources.configuration
                    configuration.setLocale(locale)
                    resources.updateConfiguration(configuration, resources.displayMetrics)
                    recreate()
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        registerReceiver(languageChangeReceiver, IntentFilter("com.example.ACTION_LANGUAGE_CHANGED"))
    }

    override fun onPause() {
        super.onPause()
    }
}