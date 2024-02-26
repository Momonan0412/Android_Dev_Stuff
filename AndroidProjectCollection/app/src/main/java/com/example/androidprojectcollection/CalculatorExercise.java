package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class CalculatorExercise extends AppCompatActivity implements View.OnClickListener{
    TextView tV1, tV2;
    Button[] btn = new Button[10];
    Button  btnAdd,  btnSub,  btnDiv,  btnMul,  btnEqual;
    Button  btnClear, btnAllClear, btnOpen, btnClose, btnDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_exercise);
        tV1 = findViewById(R.id.solutionView);
        tV2 = findViewById(R.id.resutView);

        for (int i = 1; i <= 9; i++) {
            int btnID = getResources().getIdentifier("btn_" + i, "id", getPackageName());
            btn[i] = findViewById(btnID);
        }

        btnAdd = findViewById(R.id.btn_add);
        btnSub = findViewById(R.id.btn_sub);
        btnDiv = findViewById(R.id.btn_divide);
        btnMul = findViewById(R.id.btn_multi);
        btnEqual = findViewById(R.id.btn_equal);

        btnClear = findViewById(R.id.btn_c);
        btnAllClear = findViewById(R.id.btn_AllClear);
        btnOpen = findViewById(R.id.btn_open_b);
        btnClose = findViewById(R.id.btn_close_c);
        btnDot = findViewById(R.id.btn_dot);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;

    }
}