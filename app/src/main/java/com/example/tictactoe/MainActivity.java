package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    int flag = 0;
    int count = 0;
    String s1, s2;

    TextView toasttxt;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Intent from1 = getIntent();
        s1 = from1.getStringExtra("name1");
        s2 = from1.getStringExtra("name2");


        toast = new Toast(getApplicationContext());
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) View view = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.viewContent));
        toast.setView(view);
        toasttxt = view.findViewById(R.id.toasttxt);

    }

    private void init() {
        btn1 = findViewById((R.id.btn1));
        btn2 = findViewById((R.id.btn2));
        btn3 = findViewById((R.id.btn3));
        btn4 = findViewById((R.id.btn4));
        btn5 = findViewById((R.id.btn5));
        btn6 = findViewById((R.id.btn6));
        btn7 = findViewById((R.id.btn7));
        btn8 = findViewById((R.id.btn8));
        btn9 = findViewById((R.id.btn9));

    }

    public void check(View view) {
        Button btnnow = (Button) view;

        if (btnnow.getText().toString().equals("")) {

            count++;

            if (flag == 0) {
                btnnow.setText("X");
                btnnow.setTextColor(getResources().getColor(R.color.black));
                flag = 1;
            } else {
                btnnow.setText("O");
                btnnow.setTextColor(getResources().getColor(R.color.green));
                flag = 0;
            }

            if (count >= 5) {

                b1 = btn1.getText().toString();
                b2 = btn2.getText().toString();
                b3 = btn3.getText().toString();
                b4 = btn4.getText().toString();
                b5 = btn5.getText().toString();
                b6 = btn6.getText().toString();
                b7 = btn7.getText().toString();
                b8 = btn8.getText().toString();
                b9 = btn9.getText().toString();

                if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
                    if (b1.equals("X")) {
                        //Toast.makeText(this, "Winner Is :" + s1, Toast.LENGTH_SHORT).show();
                        toasttxt.setText("Winner Is : " + s1);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        toasttxt.setText("Winner Is : " + s2);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    }
                    restart();


                } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
                    if (b4.equals("X")) {
                        toasttxt.setText("Winner Is : " + s1);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        toasttxt.setText("Winner Is : " + s2);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    }
                    restart();


                } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
                    if (b7.equals("X")) {
                        toasttxt.setText("Winner Is : " + s1);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        toasttxt.setText("Winner Is : " + s2);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    }
                    restart();


                } else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
                    if (b1.equals("X")) {
                        toasttxt.setText("Winner Is : " + s1);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        toasttxt.setText("Winner Is : " + s2);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    }
                    restart();


                } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
                    if (b2.equals("X")) {
                        toasttxt.setText("Winner Is : " + s1);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        toasttxt.setText("Winner Is : " + s2);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    }
                    restart();


                } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
                    if (b3.equals("X")) {
                        toasttxt.setText("Winner Is : " + s1);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        toasttxt.setText("Winner Is : " + s2);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    }
                    restart();


                } else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
                    if (b1.equals("X")) {
                        toasttxt.setText("Winner Is : " + s1);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        toasttxt.setText("Winner Is : " + s2);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    }
                    restart();


                } else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")) {
                    if (b3.equals("X")) {
                        toasttxt.setText("Winner Is : " + s1);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        toasttxt.setText("Winner Is : " + s2);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                    }
                    restart();
                } else if (count == 9) {
                    toasttxt.setText("Match Tied");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    restart();
                }


            }


        }


    }

    private void restart() {

        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        flag = 0;
        count = 0;
    }
}



