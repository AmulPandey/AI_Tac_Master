package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ChoseSide_activity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choseside)
        val btn: Button
        val p1: EditText
        val p2: EditText
        btn = findViewById(R.id.btn)
        p1 = findViewById(R.id.p1)
        p2 = findViewById(R.id.p2)
        btn.setOnClickListener {
            val input1 = p1.text.toString()
            val input2 = p2.text.toString()
            val inext: Intent
            inext = Intent(this@ChoseSide_activity, MainActivity::class.java)
            inext.putExtra("name1", input1)
            inext.putExtra("name2", input2)
            startActivity(inext)
        }
    }
}