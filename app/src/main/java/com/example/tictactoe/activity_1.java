package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_1 extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        Button btn;
        EditText p1,p2;

        btn=findViewById(R.id.btn);
        p1=findViewById(R.id.p1);
        p2=findViewById(R.id.p2);





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input1 = p1.getText().toString();
                String input2 = p2.getText().toString();


                Intent inext;
                inext=new Intent(activity_1.this,MainActivity.class);
                inext.putExtra("name1",input1);
                inext.putExtra("name2",input2);

                startActivity(inext);
            }
        });

    }
}