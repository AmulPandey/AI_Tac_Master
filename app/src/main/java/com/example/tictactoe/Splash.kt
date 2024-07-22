package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val ihome: Intent
            ihome = Intent(this@Splash, ChoseOpponent_activity::class.java)
            startActivity(ihome)
            finish()
        }, 3050)
    }
}