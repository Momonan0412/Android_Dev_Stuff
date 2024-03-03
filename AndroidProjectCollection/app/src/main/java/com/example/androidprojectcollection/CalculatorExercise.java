package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class CalculatorExercise extends AppCompatActivity implements View.OnClickListener{

    String data = "";
    TextView tV1, tV2;
    MaterialButton[] btn = new MaterialButton[10];
    MaterialButton  btnAdd,  btnSub,  btnDiv,  btnMul,  btnEqual;
    MaterialButton  btnClear, btnAllClear, btnOpen, btnClose, btnDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_exercise);

        tV1 = findViewById(R.id.solutionView);
        tV2 = findViewById(R.id.resutView);

        assignView(btn[0],R.id.btn_0);
        assignView(btn[1],R.id.btn_1);
        assignView(btn[2],R.id.btn_2);
        assignView(btn[3],R.id.btn_3);
        assignView(btn[4],R.id.btn_4);
        assignView(btn[5],R.id.btn_5);
        assignView(btn[6],R.id.btn_6);
        assignView(btn[7],R.id.btn_7);
        assignView(btn[8],R.id.btn_8);
        assignView(btn[9],R.id.btn_9);

        assignView(btnAdd,R.id.btn_add);
        assignView(btnSub,R.id.btn_sub);
        assignView(btnDiv,R.id.btn_divide);
        assignView(btnMul,R.id.btn_multi);
        assignView(btnEqual,R.id.btn_equal);

        assignView(btnClear,R.id.btn_c);
        assignView(btnAllClear,R.id.btn_AllClear);
        assignView(btnOpen,R.id.btn_open_b);
        assignView(btnClose,R.id.btn_close_c);
        assignView(btnDot,R.id.btn_dot);
    }
    private void assignView(MaterialButton button, int ID){
        button = findViewById(ID);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        if (buttonText.equals("C")) {
            if (data.length() > 0) {
                data = data.substring(0, data.length() - 1);
            }
        } else if (buttonText.equals("AC")) {
            data = "";
        } else if (buttonText.equals("=")) {
            String result = getResult(data);
            tV2.setText(result.equals("Err") ? "" : result);
            return;
        } else {
            data += buttonText;
        }

        // Update TextViews
        tV1.setText(data);
        tV2.setText("");
    }

    public String getResult(String data){
        String res;
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            res = context.evaluateString(scriptable, data, "JavaScript", 1, null).toString();
            if(res.endsWith(".0")){
                res = res.replace(".0", "");
            }
        } catch (Exception e){
            return "Err";
        }
        return res;
    }
}