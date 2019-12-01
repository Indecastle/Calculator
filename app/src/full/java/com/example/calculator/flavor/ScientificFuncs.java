package com.example.calculator.flavor;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.calculator.CalculatorController;
import com.example.calculator.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScientificFuncs extends Fragment implements View.OnClickListener {
    private CalculatorController calculatorController;
    public Integer textSize;

    public ScientificFuncs() {
        calculatorController = CalculatorController.getInstance();
        textSize =10;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setTextSize(textSize);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scientific_funcs, container, false);
        view.findViewById(R.id.sqrt).setOnClickListener(this);
        view.findViewById(R.id.sqrt3).setOnClickListener(this);
        view.findViewById(R.id.squared).setOnClickListener(this);
        view.findViewById(R.id.cube).setOnClickListener(this);
        view.findViewById(R.id.ten).setOnClickListener(this);
        view.findViewById(R.id.exp).setOnClickListener(this);
        view.findViewById(R.id.sin).setOnClickListener(this);
        view.findViewById(R.id.cos).setOnClickListener(this);
        view.findViewById(R.id.tan).setOnClickListener(this);
        view.findViewById(R.id.log).setOnClickListener(this);
        view.findViewById(R.id.lg).setOnClickListener(this);
        view.findViewById(R.id.mc).setOnClickListener(this);
        view.findViewById(R.id.mPlus).setOnClickListener(this);
        view.findViewById(R.id.mr).setOnClickListener(this);
        view.findViewById(R.id.anyDegree).setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        //calculatorController.buttonClickHandler(getResources().getResourceEntryName(view.getId()));
        switch (view.getId()) {

            case R.id.sqrt:
                calculatorController.buttonClickHandler("sqrt");
                break;
            case R.id.sqrt3:
                calculatorController.buttonClickHandler("sqrt3");
                break;
            case R.id.ten:
                calculatorController.buttonClickHandler("ten");
                break;
            case R.id.squared:
                calculatorController.buttonClickHandler("squared");
                break;
            case R.id.cube:
                calculatorController.buttonClickHandler("cube");
                break;
            case R.id.exp:
                calculatorController.buttonClickHandler("exp");
                break;
            case R.id.sin:
                calculatorController.buttonClickHandler("sin");
                break;
            case R.id.cos:
                calculatorController.buttonClickHandler("cos");
                break;
            case R.id.tan:
                calculatorController.buttonClickHandler("tan");
                break;
            case R.id.log:
                calculatorController.buttonClickHandler("log");
                break;
            case R.id.lg:
                calculatorController.buttonClickHandler("lg");
                break;
            case R.id.mc:
                calculatorController.buttonClickHandler("mc");
                break;
            case R.id.mPlus:
                calculatorController.buttonClickHandler("mPlus");
                break;
            case R.id.mr:
                calculatorController.buttonClickHandler("mr");
                break;
            case R.id.anyDegree:
                calculatorController.buttonClickHandler("anyDegree");
                break;
        }
    }

    public void setTextSize(Integer size) {
        this.textSize = size;
        View view = getView();
        ((Button)view.findViewById(R.id.sqrt)).setTextSize(size);
        ((Button)view.findViewById(R.id.sqrt3)).setTextSize(size);
        ((Button)view.findViewById(R.id.squared)).setTextSize(size);
        ((Button)view.findViewById(R.id.cube)).setTextSize(size);
        ((Button)view.findViewById(R.id.ten)).setTextSize(size);
        ((Button)view.findViewById(R.id.exp)).setTextSize(size);
        ((Button)view.findViewById(R.id.sin)).setTextSize(size);
        ((Button)view.findViewById(R.id.cos)).setTextSize(size);
        ((Button)view.findViewById(R.id.tan)).setTextSize(size);
        ((Button)view.findViewById(R.id.log)).setTextSize(size);
        ((Button)view.findViewById(R.id.lg)).setTextSize(size);
        ((Button)view.findViewById(R.id.mc)).setTextSize(size);
        ((Button)view.findViewById(R.id.mPlus)).setTextSize(size);
        ((Button)view.findViewById(R.id.mr)).setTextSize(size);
        ((Button)view.findViewById(R.id.anyDegree)).setTextSize(size);
    }

}
