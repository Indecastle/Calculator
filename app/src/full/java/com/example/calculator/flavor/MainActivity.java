package com.example.calculator.flavor;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;

import com.example.calculator.CalculatorController;
import com.example.calculator.NumsAndBasicFuncs;
import com.example.calculator.R;

public class MainActivity extends AppCompatActivity {
    private TextView calculatorText;
    private TextView operationText;
    private CalculatorController calculatorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorText = findViewById(R.id.calcText);
        operationText = findViewById(R.id.operationText);

        Consumer<String> updateCalculatorText = new Consumer<String>() {
            @Override
            public void accept(String string) {
                calculatorText.setText(string);
            }
        };
        Consumer<String> updateOperationText = new Consumer<String>() {
            @Override
            public void accept(String string) {
                operationText.setText(string);
            }
        };

        // get an instance of controller and set calculator text updating method to it
        calculatorController = CalculatorController.getInstance();
        calculatorController.setUpdateCalculatorText(updateCalculatorText, updateOperationText);

        // set fragments into their containers
        final LinearLayout basicFragmentContainer = findViewById(R.id.basic_fragment_container);
        final LinearLayout scientificFragmentContainer = findViewById(R.id.scientific_fragment_container);
        androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ScientificFuncs scientificFuncs = new ScientificFuncs();
        fragmentTransaction.replace(R.id.scientific_fragment_container, scientificFuncs);
        NumsAndBasicFuncs numsAndBasicFuncs = new NumsAndBasicFuncs();
        fragmentTransaction.replace(R.id.basic_fragment_container, numsAndBasicFuncs);

        fragmentTransaction.commit();
        //NumsAndBasicFuncs numsAndBasicFuncs2 = (NumsAndBasicFuncs)fragmentManager.findFragmentById(R.id.scientific_fragment_container);

        // set visibility depending on device orientation
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            numsAndBasicFuncs.textSize = 40;
            scientificFuncs.textSize = 40;
            scientificFragmentContainer.setVisibility(View.INVISIBLE);

            Button changeMode = findViewById(R.id.change_mode);
            changeMode.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (scientificFragmentContainer.getVisibility() == View.INVISIBLE) {
                        basicFragmentContainer.setVisibility(View.INVISIBLE);
                        scientificFragmentContainer.setVisibility(View.VISIBLE);
                    }
                    else {
                        scientificFragmentContainer.setVisibility(View.INVISIBLE);
                        basicFragmentContainer.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
        else{
            numsAndBasicFuncs.textSize = 20;
            scientificFuncs.textSize = 20;
        }


    }
}
