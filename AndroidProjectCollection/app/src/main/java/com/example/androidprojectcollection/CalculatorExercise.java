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
import java.util.Stack;

public class CalculatorExercise extends AppCompatActivity implements View.OnClickListener{
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
        String data = tV1.getText().toString();
        if(buttonText.equals("C")){
            if(data.length() == 0) return;
            data = data.substring(0, data.length() - 1);
            tV1.setText(data);
            return;
        }
        if(buttonText.equals("AC")){
            if(data.length() == 0) return;
            tV1.setText("");
            tV2.setText("");
            return;
        }
        if(buttonText.equals("=")){
            String res = getResult(data);
            if(res.equals("Err")){
                tV2.setText("");
            }else{
                tV2.setText(res);
            }
            return;
        }
        data = data + buttonText;
        tV1.setText(data);
    }
    public String getResult(String data){
        String res;
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            res =  context.evaluateString(scriptable, data, "JavaScript", 1, null).toString();
            if(res.endsWith(".0")) res = res.replace(".0", "");
        } catch (Exception e){
            return "Err";
        }
        return res;
    }
}