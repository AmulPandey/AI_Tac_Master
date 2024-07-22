package com.example.tictactoe

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView


class PlaywithAI_activity : AppCompatActivity() {

    private lateinit var btns: List<Button>
    private var currentPlayer = "X"
    private var moves = 0
    private lateinit var s1: String
    private lateinit var s2: String
    private lateinit var toast: Toast
    private var xWinCount = 0
    private var oWinCount = 0
    private lateinit var xWinCountTextView: TextView
    private lateinit var oWinCountTextView: TextView
    private lateinit var lottieAnimationView: LottieAnimationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        s1 = intent.getStringExtra("name1") ?: "You"
        s2 = intent.getStringExtra("name2") ?: "AI"
        toast = Toast(applicationContext)
        xWinCountTextView = findViewById(R.id.xWinCount)
        oWinCountTextView = findViewById(R.id.oWinCount)
        lottieAnimationView = findViewById(R.id.loadinganim)


    }

    private fun initViews() {
        btns = listOf(
            findViewById(R.id.btn1), findViewById(R.id.btn2), findViewById(R.id.btn3),
            findViewById(R.id.btn4), findViewById(R.id.btn5), findViewById(R.id.btn6),
            findViewById(R.id.btn7), findViewById(R.id.btn8), findViewById(R.id.btn9)
        )
    }

    fun check(view: View) {
        val button = view as Button
        if (button.text.isEmpty()) {
            button.text = currentPlayer
            moves++
            if (currentPlayer == "X") {
                button.setTextColor(resources.getColor(R.color.green)) // Set "X" text color to red
            } else {
                button.setTextColor(resources.getColor(R.color.black)) // Set "O" text color to blue
            }
            if (checkWin()) {
                displayWinner()
            } else if (moves == 9) {
                displayDraw()
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
                if (currentPlayer == "O") {
                    // AI's turn (O)
                    makeAITurn()
                }
            }
        }
    }

    private fun makeAITurn() {
        lottieAnimationView.visibility = View.VISIBLE
        lottieAnimationView.playAnimation()
        Handler().postDelayed({
            val bestMove = findBestMove()
            if (bestMove!= -1) {
                btns[bestMove].performClick()
            }
            lottieAnimationView.cancelAnimation()
            lottieAnimationView.visibility = View.GONE
        }, 500)
    }

    private fun findBestMove(): Int {
        var bestScore = Int.MIN_VALUE
        var bestMove = -1

        for (i in btns.indices) {
            if (btns[i].text.isEmpty()) {
                btns[i].text = "O"
                val score = minimax(btns, 0, false)
                btns[i].text = ""

                if (score > bestScore) {
                    bestScore = score
                    bestMove = i
                }
            }
        }
        return bestMove
    }

    private fun minimax(board: List<Button>, depth: Int, isMaximizing: Boolean): Int {
        val result = checkGameResult()
        if (result == 1) {
            return 10 - depth
        } else if (result == -1) {
            return depth - 10
        } else if (result == 0) {
            return 0
        }

        if (isMaximizing) {
            var bestScore = Int.MIN_VALUE
            for (i in board.indices) {
                if (board[i].text.isEmpty()) {
                    board[i].text = "O"
                    val score = minimax(board, depth + 1, false)
                    board[i].text = ""
                    bestScore = maxOf(score, bestScore)
                }
            }
            return bestScore
        } else {
            var bestScore = Int.MAX_VALUE
            for (i in board.indices) {
                if (board[i].text.isEmpty()) {
                    board[i].text = "X"
                    val score = minimax(board, depth + 1, true)
                    board[i].text = ""
                    bestScore = minOf(score, bestScore)
                }
            }
            return bestScore
        }
    }

    private fun checkGameResult(): Int {
        val lines = arrayOf(
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
        )

        for (line in lines) {
            if (btns[line[0]].text == "X" && btns[line[1]].text == "X" && btns[line[2]].text == "X") {
                return -1 // Player wins
            } else if (btns[line[0]].text == "O" && btns[line[1]].text == "O" && btns[line[2]].text == "O") {
                return 1 // AI wins
            }
        }

        for (i in btns.indices) {
            if (btns[i].text.isEmpty()) {
                return 2 // Game still ongoing
            }
        }

        return 0 // It's a draw
    }

    private fun checkWin(): Boolean {
        val lines = arrayOf(
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
        )

        for (line in lines) {
            if (btns[line[0]].text.isNotEmpty() &&
                btns[line[0]].text == btns[line[1]].text &&
                btns[line[0]].text == btns[line[2]].text
            ) {
                return true
            }
        }
        return false
    }


    private fun displayWinner() {
        val winner = if (currentPlayer == "X") {
            xWinCount++
            s1
        } else {
            oWinCount++
            s2
        }

        showToastforwin("$winner Wins!")
        updateWinCountDisplay()
        resetBoard()
    }

    private fun displayDraw() {
        showToast("It's a draw!")
        resetBoard()
    }

    private fun updateWinCountDisplay() {
        xWinCountTextView.text = "X Wins: $xWinCount"
        oWinCountTextView.text = "O Wins: $oWinCount"
    }


    private fun showToastforwin(message: String) {
        toast.cancel() // Cancel previous toast to prevent overlap
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.viewContent))
        val toastMessage = layout.findViewById<TextView>(R.id.toasttxt)
        toastMessage.text = message

        toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

    private fun resetBoard() {
        for (button in btns) {
            button.text = ""
        }
        currentPlayer = "X"
        moves = 0
    }
}
