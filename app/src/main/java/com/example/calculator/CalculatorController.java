package com.example.calculator;

import androidx.core.util.Consumer;

public class CalculatorController {

    private static final CalculatorController INSTANCE = new CalculatorController();

    private CalculatorController() {
        currentCalculatorText = "0";
        calculatorModel = CalculatorModel.getInstance();
        isResultOfOperationShown = false;
    }

    public static CalculatorController getInstance() {
        return INSTANCE;
    }

    private CalculatorModel calculatorModel;
    private Consumer<String> updateCalculatorText;
    private String currentCalculatorText;

    // for checking if result of previous operation or just general number is shown now
    private boolean isResultOfOperationShown;

    public void setUpdateCalculatorText(Consumer<String> updateCalculatorText) {
        this.updateCalculatorText = updateCalculatorText;
        setTextToCalculator(currentCalculatorText);
    }

    public void buttonClickHandler(String data) {
        if (isNumeric(data)) {
            setTextToCalculator(addDigit(data));
        }
        else{
            switch(data){
                case "pm":
                    setTextToCalculator(setPosToNeg());
                    break;
                case ".":
                    setTextToCalculator(setPoint());
                    break;
                case "del":
                    setTextToCalculator(delText());
                    break;
                case "pi":
                    setPi();
                    break;
                case "e":
                    setE();
                    break;
                case "mr":
                    setTextToCalculator(calculatorModel.getResultOfOperation("mr"));
                    isResultOfOperationShown = true;
                    break;
                case "mc":
                    calculatorModel.clearMemory();
                    break;
                default:
                    setResultOfOperation(data);

            }
        }
    }

    // set result of operation as a current calculator text
    private void setResultOfOperation(String operation) {
        String result;

        try {
            double currentOperator = Double.valueOf(currentCalculatorText);
            result = calculatorModel.getResultOfOperation(operation, currentOperator);
            setTextToCalculator(result);
            isResultOfOperationShown = true;
        }
        catch (NumberFormatException e) {
            result = "Error";
        }

    }

    // adding a digit to current number if not a zero, result of previous operation or "Error"
    private String addDigit(String digit) {
        String result;
        if (isResultOfOperationShown) {
            result = digit;
            isResultOfOperationShown = false;
        }
        else {
            result = (currentCalculatorText != "0") ? (currentCalculatorText != "Error" ? currentCalculatorText + digit : digit) : digit;
        }
        return result;
    }

    // change positive to negative or vice versa if current calculator text is a number and not a result of previous operation
    private String setPosToNeg() {
        String result;

        try {
            if (isResultOfOperationShown) {
                result = currentCalculatorText;
            }
            else {
                result = (currentCalculatorText.contains(".")) ? String.valueOf(Float.valueOf(currentCalculatorText) * (-1)) : Integer.toString(Integer.valueOf(currentCalculatorText) * (-1));
            }
        }
        catch (NumberFormatException e) {
            result = "Error";
        }


        return result;
    }

    // adding a point to current number if not set before or result of previous operation
    private String setPoint() {
        String result;
        if (isResultOfOperationShown) {
            result = "0.";
            isResultOfOperationShown = false;
        }
        else {
            result = (currentCalculatorText.contains(".")) ? currentCalculatorText : (currentCalculatorText != "Error" ? currentCalculatorText + "." : "0.");
        }
        return result;
    }

    // set pi to text view
    private void setPi() {
        setTextToCalculator("3.141592653589793");
        isResultOfOperationShown = true;
    }

    // set e to text view
    private void setE() {
        setTextToCalculator("2.718281828459045");
        isResultOfOperationShown = true;
    }


    // delete on digit of calculator text if it's number and not a result of previous operation
    private String delText() {
        String result = "";

        if ((isNumeric(currentCalculatorText)) &  (!isResultOfOperationShown) & (currentCalculatorText.length() > 1)) {
            StringBuilder currentTextBuffer = new StringBuilder(currentCalculatorText);
            result = String.valueOf(currentTextBuffer.deleteCharAt(currentCalculatorText.length() - 1));
        }
        else if (isResultOfOperationShown) {
            result = "0";
            isResultOfOperationShown = false;
        }
        else if ((currentCalculatorText.length() == 1) | (!isNumeric(currentCalculatorText))) {
            result = "0";
        }
        return result;
    }

    private void setTextToCalculator(String string) {
        updateCalculatorText.accept(string);
        currentCalculatorText = string;
    }

    // if the number ends with .0 then delete this part
    private String deletePointZeroIfExist(String string) {
        if (string.length() > 2) {
            string = (string.charAt(string.length() - 1) == '0' & (string.charAt(string.length() - 2) == '.')) ? String.valueOf((new StringBuilder(string)).delete(string.length() - 2, string.length())) : string;
        }

        return string;
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
}
