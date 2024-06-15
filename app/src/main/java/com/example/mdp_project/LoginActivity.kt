package com.example.mdp_project

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.Locale

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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