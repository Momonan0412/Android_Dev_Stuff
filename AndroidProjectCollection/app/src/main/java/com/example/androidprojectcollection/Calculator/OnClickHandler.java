package com.example.androidprojectcollection.Calculator;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.androidprojectcollection.MakeToast;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class OnClickHandler implements View.OnClickListener, MakeToast {
    String data;
    ArrayList<String> mdas = new ArrayList<>();
    Calculator calculator;
    Activity activity;
    TextView TextView1, TextView2;
    public OnClickHandler(String data, Calculator calculator, Activity activity) {
        this.data = data;
        this.calculator = calculator;
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        if (buttonText.equals("C")) {
            if (!data.isEmpty()) {
                data = data.substring(0, data.length() - 1);
            }
        } else if (buttonText.equals("AC")) {
            data = "";
        } else if (buttonText.equals("=") && isLastCharDigit(mdas.get(mdas.size() - 1))) {
            String result = calculator.getResult(mdas);
            this.TextView2.setText(result.equals("Err") ? "" : result);
            String res = result;
            System.out.println("After" + res);
            return;
        } else if (buttonText.equals("=")) {
            showToast(activity, "Warning: The last character is an operator!");
            return;
        }
// -------------------------------------------------------------------------------------------------
        // LOGIC CHANGING OPERATOR!
        else if (data.endsWith("+") && (buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/"))) {
            data = data.substring(0, data.length() - 1) + buttonText;
        } else if (data.endsWith("-") && (buttonText.equals("+") || buttonText.equals("*") || buttonText.equals("/"))) {
            data = data.substring(0, data.length() - 1) + buttonText;
        } else if (data.endsWith("*") && (buttonText.equals("-") || buttonText.equals("+") || buttonText.equals("/"))) {
            data = data.substring(0, data.length() - 1) + buttonText;
        } else if (data.endsWith("/") && (buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("+"))) {
            data = data.substring(0, data.length() - 1) + buttonText;
        } else if (buttonText.equals("+") && data.endsWith("+") ||
                buttonText.equals("-") && data.endsWith("-") ||
                buttonText.equals("*") && data.endsWith("*") ||
                buttonText.equals("/") && data.endsWith("/")) {
            data = data.substring(0, data.length() - 1);
            data += buttonText;
        }
// -------------------------------------------------------------------------------------------------
        else {
            mdas.add(buttonText);
            data += buttonText;
        }
// -------------------------------------------------------------------------------------------------
        if ((!data.endsWith("+") && !data.endsWith("-") && !data.endsWith("*") && !data.endsWith("/")) && data.length() > 2) {
            String seqResult = "";
            try {
                seqResult = calculator.sequentialResult(data);
                if (seqResult.endsWith(".0")) {
                    seqResult = seqResult.replace(".0", "");
                }
                data = seqResult;
            } catch (ArithmeticException e) {
                e.printStackTrace();
            } catch (OperatorNotFoundException e) {
                e.printStackTrace();
            }
        }
        this.TextView1.setText(data);
        this.TextView2.setText("");
    }
    public void assignView(View view, int ID) {
        view = activity.findViewById(ID);
        view.setOnClickListener(this);
    }

    public void setTextView1(TextView textView1) {
        TextView1 = textView1;
    }

    public void setTextView2(TextView textView2) {
        TextView2 = textView2;
    }
    private static boolean isLastCharDigit(String str) {
        if (str.length() > 0) {
            char lastChar = str.charAt(str.length() - 1);
            return Character.isDigit(lastChar);
        }
        return false;
    }
}
