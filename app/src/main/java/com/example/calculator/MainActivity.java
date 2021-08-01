package com.example.calculator;
//https://github.com/Practical-Coding3/calculatorApp/blob/master/app/src/main/java/com/example/calculator/MainActivity.java
//link for the project code
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd){
        String oldstring = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftstring = oldstring.substring(0,cursorPos);
        String rightstring = oldstring.substring(cursorPos);

        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
        }
        else{
            display.setText(String.format("%s%s%s",leftstring,strToAdd,rightstring));
            display.setSelection(cursorPos+1);

        }

    }


    public void zeroBTN(View view){
        updateText("0");

    }

    public void oneBTN(View view){
        updateText("1");


    }

    public void twoBTN(View view){
        updateText("2");

    }

    public void threeBTN(View view){
        updateText("3");

    }

    public void fourBTN(View view){
        updateText("4");

    }

    public void fiveBTN(View view){
        updateText("5");

    }

    public void sixBTN(View view){
        updateText("6");

    }

    public void sevenBTN(View view){
        updateText("7");

    }

    public void eightBTN(View view){
        updateText("8");

    }

    public void nineBTN(View view){
        updateText("9");

    }

    public void clearBTN(View view){
        display.setText("");

    }

    public void paranthesisBTN(View view){
        int cursorPos = display.getSelectionStart();
        int openparanthesis = 0;
        int closedparanthesis = 0;
        int textlength = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openparanthesis += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closedparanthesis += 1;
            }
        }

        if(openparanthesis == closedparanthesis || display.getText().toString().substring(textlength-1,textlength).equals("(")){
            updateText("(");
            display.setSelection(cursorPos+1);
        }
        else if(closedparanthesis < openparanthesis || !display.getText().toString().substring(textlength-1,textlength).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);




    }

    public void exponentBTN(View view){
        updateText("^");

    }

    public void divideBTN(View view){
        updateText("÷");

    }

    public void multiplyBTN(View view){
        updateText("*");

    }

    public void addBTN(View view){
        updateText("+");

    }

    public void subtractBTN(View view){
        updateText("-");

    }

    public void equalsBTN(View view){
        String userexpress = display.getText().toString();

        userexpress = userexpress.replaceAll("÷","/");
        userexpress = userexpress.replaceAll("×","*");

        Expression exp = new Expression(userexpress);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length()); //updates the cursor position

    }

    public void PlusOrMinusBTN(View view){
        updateText("+/-");

    }

    public void decimalBTN(View view){
        updateText(".");

    }

    public void backspaceBTN(View view){
        int cursorPos = display.getSelectionStart();
        int textlength = display.getText().length();

        if(cursorPos!=0 && textlength!=0) {
            SpannableStringBuilder select = (SpannableStringBuilder) display.getText();
            select.replace(cursorPos - 1, cursorPos, "");
            display.setText(select);
            display.setSelection(cursorPos-1);

        }

    }
}