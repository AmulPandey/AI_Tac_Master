package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        s1 = intent.getStringExtra("name1") ?: ""
        s2 = intent.getStringExtra("name2") ?: ""
        toast = Toast(applicationContext)
        xWinCountTextView = findViewById(R.id.xWinCount)
        oWinCountTextView = findViewById(R.id.oWinCount)

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
            }
        }
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


/*

package com.example.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var btn1: Button? = null
    var btn2: Button? = null
    var btn3: Button? = null
    var btn4: Button? = null
    var btn5: Button? = null
    var btn6: Button? = null
    var btn7: Button? = null
    var btn8: Button? = null
    var btn9: Button? = null
    var b1: String? = null
    var b2: String? = null
    var b3: String? = null
    var b4: String? = null
    var b5: String? = null
    var b6: String? = null
    var b7: String? = null
    var b8: String? = null
    var b9: String? = null
    var flag = 0
    var count = 0
    var s1: String? = null
    var s2: String? = null
    var toasttxt: TextView? = null
    var toast: Toast? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        val from1 = intent
        s1 = from1.getStringExtra("name1")
        s2 = from1.getStringExtra("name2")

        toast = Toast(applicationContext)

        // Inflate the custom toast layout
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.viewContent))

        // Initialize the TextView in the custom toast layout
        toasttxt = layout.findViewById(R.id.toasttxt)

        // Set the custom layout for the toast
        toast!!.view = layout
    }


    private fun init() {
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
    }

    fun check(view: View) {
        val btnnow = view as Button
        if (btnnow.text.toString() == "") {
            count++
            if (flag == 0) {
                btnnow.text = "X"
                btnnow.setTextColor(resources.getColor(R.color.black))
                flag = 1
            } else {
                btnnow.text = "O"
                btnnow.setTextColor(resources.getColor(R.color.green))
                flag = 0
            }
            if (count >= 5) {
                b1 = btn1!!.text.toString()
                b2 = btn2!!.text.toString()
                b3 = btn3!!.text.toString()
                b4 = btn4!!.text.toString()
                b5 = btn5!!.text.toString()
                b6 = btn6!!.text.toString()
                b7 = btn7!!.text.toString()
                b8 = btn8!!.text.toString()
                b9 = btn9!!.text.toString()
                if (b1 == b2 && b2 == b3 && b1 != "") {
                    if (b1 == "X") {
                        //Toast.makeText(this, "Winner Is :" + s1, Toast.LENGTH_SHORT).show();
                        toasttxt!!.text = "Winner Is : $s1"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    } else {
                        toasttxt!!.text = "Winner Is : $s2"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    }
                    restart()
                } else if (b4 == b5 && b5 == b6 && b4 != "") {
                    if (b4 == "X") {
                        toasttxt!!.text = "Winner Is : $s1"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    } else {
                        toasttxt!!.text = "Winner Is : $s2"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    }
                    restart()
                } else if (b7 == b8 && b8 == b9 && b7 != "") {
                    if (b7 == "X") {
                        toasttxt!!.text = "Winner Is : $s1"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    } else {
                        toasttxt!!.text = "Winner Is : $s2"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    }
                    restart()
                } else if (b1 == b4 && b4 == b7 && b1 != "") {
                    if (b1 == "X") {
                        toasttxt!!.text = "Winner Is : $s1"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    } else {
                        toasttxt!!.text = "Winner Is : $s2"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    }
                    restart()
                } else if (b2 == b5 && b5 == b8 && b2 != "") {
                    if (b2 == "X") {
                        toasttxt!!.text = "Winner Is : $s1"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    } else {
                        toasttxt!!.text = "Winner Is : $s2"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    }
                    restart()
                } else if (b3 == b6 && b6 == b9 && b3 != "") {
                    if (b3 == "X") {
                        toasttxt!!.text = "Winner Is : $s1"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    } else {
                        toasttxt!!.text = "Winner Is : $s2"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    }
                    restart()
                } else if (b1 == b5 && b5 == b9 && b1 != "") {
                    if (b1 == "X") {
                        toasttxt!!.text = "Winner Is : $s1"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    } else {
                        toasttxt!!.text = "Winner Is : $s2"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    }
                    restart()
                } else if (b3 == b5 && b5 == b7 && b3 != "") {
                    if (b3 == "X") {
                        toasttxt!!.text = "Winner Is : $s1"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    } else {
                        toasttxt!!.text = "Winner Is : $s2"
                        toast!!.duration = Toast.LENGTH_LONG
                        toast!!.show()
                    }
                    restart()
                } else if (count == 9) {
                    toasttxt!!.text = "Match Tied"
                    toast!!.duration = Toast.LENGTH_LONG
                    toast!!.show()
                    restart()
                }
            }
        }
    }

    private fun restart() {
        btn1!!.text = ""
        btn2!!.text = ""
        btn3!!.text = ""
        btn4!!.text = ""
        btn5!!.text = ""
        btn6!!.text = ""
        btn7!!.text = ""
        btn8!!.text = ""
        btn9!!.text = ""
        flag = 0
        count = 0
    }
}

 */
