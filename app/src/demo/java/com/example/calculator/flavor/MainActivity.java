package com.example.calculator.flavor;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;

import com.example.calculator.CalculatorController;
import com.example.calculator.NumsAndBasicFuncs;
import com.example.calculator.R;


public class MainActivity extends AppCompatActivity {
    private TextView calculatorText;
    private CalculatorController calculatorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorText = findViewById(R.id.calcText);

        Consumer<String> updateCalculatorText = new Consumer<String>() {
            @Override
            public void accept(String string) {
                calculatorText.setText(string);
            }
        };

        // get an instance of controller and set calculator text updating method to it
        calculatorController = CalculatorController.getInstance();
        calculatorController.setUpdateCalculatorText(updateCalculatorText);

        // set fragments into their containers
        NumsAndBasicFuncs numsAndBasicFuncs = new NumsAndBasicFuncs();
        androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, numsAndBasicFuncs);
        fragmentTransaction.commit();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            numsAndBasicFuncs.size = 40;
        }
        else{
            numsAndBasicFuncs.size = 24;
        }
    }
}
