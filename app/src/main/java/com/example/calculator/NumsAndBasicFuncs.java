package com.example.calculator;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumsAndBasicFuncs extends Fragment implements View.OnClickListener {
    public Integer textSize;

    public NumsAndBasicFuncs() {
        textSize = 10;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setTextSize(textSize);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nums_and_basic_funcs_layout, container, false);
        view.findViewById(R.id.one).setOnClickListener(this);
        view.findViewById(R.id.two).setOnClickListener(this);
        view.findViewById(R.id.three).setOnClickListener(this);
        view.findViewById(R.id.four).setOnClickListener(this);
        view.findViewById(R.id.five).setOnClickListener(this);
        view.findViewById(R.id.six).setOnClickListener(this);
        view.findViewById(R.id.seven).setOnClickListener(this);
        view.findViewById(R.id.eight).setOnClickListener(this);
        view.findViewById(R.id.nine).setOnClickListener(this);
        view.findViewById(R.id.zero).setOnClickListener(this);
        view.findViewById(R.id.pm).setOnClickListener(this);
        view.findViewById(R.id.del).setOnClickListener(this);
        view.findViewById(R.id.point).setOnClickListener(this);
        view.findViewById(R.id.pi).setOnClickListener(this);
        view.findViewById(R.id.e).setOnClickListener(this);
        view.findViewById(R.id.div).setOnClickListener(this);
        view.findViewById(R.id.mult).setOnClickListener(this);
        view.findViewById(R.id.equal).setOnClickListener(this);
        view.findViewById(R.id.plus).setOnClickListener(this);
        view.findViewById(R.id.minus).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        //calculatorController.buttonClickHandler(getResources().getResourceEntryName(view.getId()));
        switch (view.getId()) {
            // digits
            case R.id.one:
                CalculatorController.getInstance().buttonClickHandler("1");
                break;
            case R.id.two:
                CalculatorController.getInstance().buttonClickHandler("2");
                break;
            case R.id.three:
                CalculatorController.getInstance().buttonClickHandler("3");
                break;
            case R.id.four:
                CalculatorController.getInstance().buttonClickHandler("4");
                break;
            case R.id.five:
                CalculatorController.getInstance().buttonClickHandler("5");
                break;
            case R.id.six:
                CalculatorController.getInstance().buttonClickHandler("6");
                break;
            case R.id.seven:
                CalculatorController.getInstance().buttonClickHandler("7");
                break;
            case R.id.eight:
                CalculatorController.getInstance().buttonClickHandler("8");
                break;
            case R.id.nine:
                CalculatorController.getInstance().buttonClickHandler("9");
                break;
            case R.id.zero:
                CalculatorController.getInstance().buttonClickHandler("0");
                break;
            case R.id.pm:
                CalculatorController.getInstance().buttonClickHandler("pm");
                break;

            // point
            case R.id.point:
                CalculatorController.getInstance().buttonClickHandler(".");
                break;

            // clear
            case R.id.del:
                CalculatorController.getInstance().buttonClickHandler("del");
                break;

            // math const
            case R.id.pi:
                CalculatorController.getInstance().buttonClickHandler("pi");
                break;
            case R.id.e:
                CalculatorController.getInstance().buttonClickHandler("e");
                break;

            // binary functions
            case R.id.div:
                CalculatorController.getInstance().buttonClickHandler("/");
                break;
            case R.id.mult:
                CalculatorController.getInstance().buttonClickHandler("*");
                break;
            case R.id.minus:
                CalculatorController.getInstance().buttonClickHandler("-");
                break;
            case R.id.plus:
                CalculatorController.getInstance().buttonClickHandler("+");
                break;
            case R.id.equal:
                CalculatorController.getInstance().buttonClickHandler("=");
                break;

        }
    }

    public void setTextSize(Integer size) {
        this.textSize = size;
        View view = getView();
        ((Button)view.findViewById(R.id.one)).setTextSize(size);
        ((Button)view.findViewById(R.id.two)).setTextSize(size);
        ((Button)view.findViewById(R.id.three)).setTextSize(size);
        ((Button)view.findViewById(R.id.four)).setTextSize(size);
        ((Button)view.findViewById(R.id.five)).setTextSize(size);
        ((Button)view.findViewById(R.id.six)).setTextSize(size);
        ((Button)view.findViewById(R.id.seven)).setTextSize(size);
        ((Button)view.findViewById(R.id.eight)).setTextSize(size);
        ((Button)view.findViewById(R.id.nine)).setTextSize(size);
        ((Button)view.findViewById(R.id.zero)).setTextSize(size);
        ((Button)view.findViewById(R.id.pm)).setTextSize(size);
        ((Button)view.findViewById(R.id.del)).setTextSize(size);
        ((Button)view.findViewById(R.id.point)).setTextSize(size);
        ((Button)view.findViewById(R.id.pi)).setTextSize(size);
        ((Button)view.findViewById(R.id.e)).setTextSize(size);
        ((Button)view.findViewById(R.id.div)).setTextSize(size);
        ((Button)view.findViewById(R.id.mult)).setTextSize(size);
        ((Button)view.findViewById(R.id.equal)).setTextSize(size);
        ((Button)view.findViewById(R.id.plus)).setTextSize(size);
        ((Button)view.findViewById(R.id.minus)).setTextSize(size);

        //((Button)view.findViewById(R.id.equal)).setText(textSize.toString());
    }
}
