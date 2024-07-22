package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

class ChoseOpponent_activity : AppCompatActivity() {
    private lateinit var opponentRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choseopponent)

        // Initialize opponentRadioGroup
        opponentRadioGroup = findViewById(R.id.opponentRadioGroup)

        // Call the startGame function when the "Start Game" button is clicked
        val startGameButton = findViewById<Button>(R.id.startGameButton)
        startGameButton.setOnClickListener {
            startGame()
        }
    }

    private fun startGame() {
        val selectedRadioButtonId = opponentRadioGroup.checkedRadioButtonId
        val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)

        val intent: Intent

        // Check which radio button is selected
        if (selectedRadioButton != null) {
            when (selectedRadioButton.text) {
                "1 vs 1 (Two Players)" -> {
                    // Start Activity 1 (replace Activity1::class.java with the actual class name)
                    intent = Intent(this, ChoseSide_activity::class.java)
                }
                "Play with AI" -> {
                    // Start ActivityMain2 (replace ActivityMain2::class.java with the actual class name)
                    intent = Intent(this, PlaywithAI_activity::class.java)
                }
                else -> {
                    // Default action (if none of the above conditions are met)
                    // You can handle this case as needed
                    intent = Intent(this, ChoseSide_activity::class.java)
                }
            }
            startActivity(intent)
        }
    }
}
