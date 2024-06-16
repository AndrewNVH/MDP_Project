package com.example.mdp_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class Start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)

//        val splashGif = findViewById<ImageView>(R.id.splashGif)
//        Glide.with(this).asGif().load(R.drawable.openingbackground).into(splashGif)

        Handler().postDelayed({
            val intent = Intent(this@Start, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },2500)
    }
}