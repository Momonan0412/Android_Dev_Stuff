package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class LayoutExercise extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6;
    Boolean iscolor = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_exercise);
        btn1 = findViewById(R.id.btnLayout);
        btn2 = findViewById(R.id.btnButtonHide);
        btn3 = findViewById(R.id.btnToastMe);
        btn4 = findViewById(R.id.btnExit);
        btn5 = findViewById(R.id.btnChangeBG);
        btn6 = findViewById(R.id.btnChangeThis);
        ConstraintLayout constraintLayout = findViewById(R.id.clChangeColor);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LayoutExercise.this, MainActivity.class);

                startActivity(intent1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn2.setVisibility(view.GONE);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LayoutExercise.this, "Furina C2!", Toast.LENGTH_SHORT).show();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutExercise.this.finish();
                finish();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iscolor)
                {
                    Random color = new Random();
                    constraintLayout.setBackgroundColor(Color.argb(255, color.nextInt(255), color.nextInt(255), color.nextInt(255)));
                    iscolor = false;
                }
                else
                {
                    constraintLayout.setBackgroundColor(Color.WHITE);
                    iscolor = true;
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random color = new Random();
                btn6.setBackgroundColor(Color.argb(255, color.nextInt(255), color.nextInt(255), color.nextInt(255)));

            }
        });
    }
}