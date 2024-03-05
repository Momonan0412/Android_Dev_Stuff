package com.example.androidprojectcollection.Calculator;

import com.example.androidprojectcollection.MakeToast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class Calculator implements MakeToast{
    public Calculator() {}
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
    public String sequentialResult(String data) throws OperatorNotFoundException {
        // Initialize variables to store the numbers and operator
        double num1 = 0;
        double num2 = 0;
        char operator = ' ';

        // Iterate through the characters in the input data
        int i;
        for (i = 0; i < data.length(); i++) {
            char currentChar = data.charAt(i);

            // Check if the current character is an operator
            if (isOperator(currentChar)) {
                // Extract the first number from the start of the string to the current index
                num1 = Double.parseDouble(data.substring(0, i).trim());

                // Set the operator
                operator = currentChar;

                // Find the next operator, if any, to get the end index of the second number
                int nextOperatorIndex = findNextOperator(data, i + 1);

                // Extract the second number from the current index to the next operator or end of the string
                num2 = Double.parseDouble(data.substring(i + 1, nextOperatorIndex).trim());

                // Update the loop index to the next operator or end of the string
                i = nextOperatorIndex - 1;

                // Perform the calculation based on the operator
                num1 = (num2 != 0) ? getSeqResult(num1, num2, operator) : num1;
            }
        }

        // Format the result and return it as a string
        return String.valueOf(num1);
    }

    private int findNextOperator(String data, int startIndex) {
        for (int i = startIndex; i < data.length(); i++) {
            if (isOperator(data.charAt(i))) {
                return i;
            }
        }
        return data.length();
    }

    public boolean isOperator(char operator){
        switch (operator){
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
        }
        return false;
    }
    double getSeqResult(double num1, double num2, char op){
        double total = 0;
        if(op == '+'){
            total = num1 + num2;
        } else if (op == '-') {
            total = num1 - num2;
        } else if (op == '*') {
            total = num1 * num2;
        } else if (op == '/') {
            if (num2 != 0) {
                total = num1 / num2;
            } else {
                throw new ArithmeticException();
            }
        }
        return total;
    }
}
