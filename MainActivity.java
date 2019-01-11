package com.frizzlefox.randmath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_sign, btn_new, btn_equal, btn_ce, btn_back;
    private TextView equation_display, number_display;
    private Stack stack;
    private String userInput;
    private Boolean sign;
    private int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keypad();
        startUp();
    }

    private void startUp() {
        GenerateEq equation = new GenerateEq();
        answer = equation.getAnswer();
        stack = new Stack();
        sign = true;
        userInput = "";
        equation_display.setText(equation.getEq());
        number_display.setText("");
    }

    private void insertStack(char a) {
        stack.push(a);

        textDisplay();
    }

    private void backSpace() {
        if (!stack.isEmpty() && !stack.peek().equals('-')) {
            stack.pop();
            textDisplay();
        }
    }

    private void clearEntry() {
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            stack.pop();
        }
        number_display.setText("");
    }

    private void checkEqual() {
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            userInput = stack.pop() + userInput;
        }
        if (size != 0 && !userInput.equals("-") && userInput.length() < 10 && Integer.valueOf(userInput) == answer) {
            number_display.setText(userInput + " Correct");
        } else {
            number_display.setText(userInput + " Incorrect");
        }
        for (int i = 0; i < userInput.length(); i++) {
            stack.push(userInput.charAt(i));
        }
        userInput = "";

        /**
         * userInput = userInout.substring(0, userInput.length() - 1);
         */
    }

    private void newEquation() {
        startUp();
    }

    private void changeSign() {
        int size = stack.size();
        if (sign) {
            sign = false;
            for (int i = 0; i < size; i++) {
                userInput = stack.pop() + userInput;
            }
            userInput = "-" + userInput;

            for (int i = 0; i < userInput.length(); i++) {
                stack.push(userInput.charAt(i));
            }
        } else {
            sign = true;
            for (int i = 0; i < size; i++) {
                if (!stack.peek().equals('-')) {
                    userInput = stack.pop() + userInput;
                } else {
                    stack.pop();
                }
            }
            for (int i = 0; i < userInput.length(); i++) {
                stack.push(userInput.charAt(i));
            }
        }
        number_display.setText(userInput);
        userInput = "";
    }

    private void textDisplay() {
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            userInput = stack.pop() + userInput;
        }
        number_display.setText(userInput);

        for (int i = 0; i < userInput.length(); i++) {
            stack.push(userInput.charAt(i));
        }
        userInput = "";
    }

    private void keypad() {

        btn_0 = (Button) findViewById(R.id.button_0);
        btn_0.setText("0");
        btn_0.setOnClickListener(clickInsertListener);

        btn_1 = (Button) findViewById(R.id.button_1);
        btn_1.setText("1");
        btn_1.setOnClickListener(clickInsertListener);

        btn_2 = (Button) findViewById(R.id.button_2);
        btn_2.setText("2");
        btn_2.setOnClickListener(clickInsertListener);

        btn_3 = (Button) findViewById(R.id.button_3);
        btn_3.setText("3");
        btn_3.setOnClickListener(clickInsertListener);

        btn_4 = (Button) findViewById(R.id.button_4);
        btn_4.setText("4");
        btn_4.setOnClickListener(clickInsertListener);

        btn_5 = (Button) findViewById(R.id.button_5);
        btn_5.setText("5");
        btn_5.setOnClickListener(clickInsertListener);

        btn_6 = (Button) findViewById(R.id.button_6);
        btn_6.setText("6");
        btn_6.setOnClickListener(clickInsertListener);

        btn_7 = (Button) findViewById(R.id.button_7);
        btn_7.setText("7");
        btn_7.setOnClickListener(clickInsertListener);

        btn_8 = (Button) findViewById(R.id.button_8);
        btn_8.setText("8");
        btn_8.setOnClickListener(clickInsertListener);

        btn_9 = (Button) findViewById(R.id.button_9);
        btn_9.setText("9");
        btn_9.setOnClickListener(clickInsertListener);

        btn_sign = (Button) findViewById(R.id.button_sign);
        btn_sign.setText("+/-");
        btn_sign.setOnClickListener(clickSpecialListener);

        btn_equal = (Button) findViewById(R.id.button_equal);
        btn_equal.setText("=");
        btn_equal.setOnClickListener(clickSpecialListener);

        btn_new = (Button) findViewById(R.id.button_new);
        btn_new.setText("New");
        btn_new.setOnClickListener(clickSpecialListener);

        btn_ce = (Button) findViewById(R.id.button_ce);
        btn_ce.setText("CE");
        btn_ce.setOnClickListener(clickSpecialListener);

        btn_back = (Button) findViewById(R.id.button_bksp);
        btn_back.setText("bksp");
        btn_back.setOnClickListener(clickSpecialListener);

        equation_display = (TextView) findViewById(R.id.eq_display);
        number_display = (TextView) findViewById(R.id.num_display);
    }


    View.OnClickListener clickInsertListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_0:
                    insertStack('0');
                    break;
                case R.id.button_1:
                    insertStack('1');
                    break;
                case R.id.button_2:
                    insertStack('2');
                    break;
                case R.id.button_3:
                    insertStack('3');
                    break;
                case R.id.button_4:
                    insertStack('4');
                    break;
                case R.id.button_5:
                    insertStack('5');
                    break;
                case R.id.button_6:
                    insertStack('6');
                    break;
                case R.id.button_7:
                    insertStack('7');
                    break;
                case R.id.button_8:
                    insertStack('8');
                    break;
                case R.id.button_9:
                    insertStack('9');
                    break;
                default:
            }
        }
    };

    View.OnClickListener clickSpecialListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_sign:
                    changeSign();
                    break;
                case R.id.button_new:
                    newEquation();
                    break;
                case R.id.button_equal:
                    checkEqual();
                    break;
                case R.id.button_bksp:
                    backSpace();
                    break;
                case R.id.button_ce:
                    clearEntry();
                    break;
                default:
            }
        }
    };
}
