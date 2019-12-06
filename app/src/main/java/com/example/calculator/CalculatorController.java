package com.example.calculator;

import androidx.core.util.Consumer;

public class CalculatorController {

    private static final CalculatorController INSTANCE = new CalculatorController();

    private CalculatorController() {
        currentCalculatorText = "0";
        isResultOfOperationShown = false;
    }

    public static CalculatorController getInstance() {
        return INSTANCE;
    }

    private Consumer<String> updateCalculatorText;
    private Consumer<String> updateOperationText;
    private String currentCalculatorText;

    // for checking if result of previous operation or just general number is shown now
    private boolean isResultOfOperationShown;

    public void setUpdateCalculatorText(Consumer<String> updateCalculatorText, Consumer<String> updateOperationText) {
        this.updateCalculatorText = updateCalculatorText;
        this.updateOperationText = updateOperationText;
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
                    delText();
                    break;
                case "pi":
                    setPi();
                    break;
                case "e":
                    setE();
                    break;
                case "mr":
                    setTextToCalculator(CalculatorModel.getInstance().getResultOfOperation("mr"));
                    isResultOfOperationShown = true;
                    break;
                case "mc":
                    CalculatorModel.getInstance().clearMemory();
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
            result = CalculatorModel.getInstance().getResultOfOperation(operation, currentOperator);
            if(operation == "=") {
                updateOperationText.accept("");
            }
            else{
                updateOperationText.accept(operation);
            }

            setTextToCalculator(result);
            isResultOfOperationShown = true;
        }
        catch (NumberFormatException e) {
            result = "Error";
        }

    }

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

    private String setPosToNeg() {
        String result;

        try {
            if (isResultOfOperationShown && false) {
                result = currentCalculatorText;
            }
            else {
                result = (currentCalculatorText.contains(".")) ? String.valueOf(Double.valueOf(currentCalculatorText) * (-1)) : Integer.toString(Integer.valueOf(currentCalculatorText) * (-1));
                currentCalculatorText = result;
                CalculatorModel.getInstance().setOperator(result);
            }
        }
        catch (NumberFormatException e) {
            result = "Error";
        }


        return result;
    }

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

    private void setPi() {
        setTextToCalculator("3.141592653589793");
        isResultOfOperationShown = true;
    }

    private void setE() {
        setTextToCalculator("2.718281828459045");
        isResultOfOperationShown = true;
    }


    private void delText() {
        String result = "";

        if ((isNumeric(currentCalculatorText)) &  (!isResultOfOperationShown) & (currentCalculatorText.length() > 1)) {
            StringBuilder currentTextBuffer = new StringBuilder(currentCalculatorText);
            result = String.valueOf(currentTextBuffer.deleteCharAt(currentCalculatorText.length() - 1));
        }
        else if (isResultOfOperationShown) {
            result = "0";
            isResultOfOperationShown = false;
            CalculatorModel.getInstance().dellOperator();
        }
        else if ((currentCalculatorText.length() == 1) | (!isNumeric(currentCalculatorText))) {
            result = "0";
            CalculatorModel.getInstance().dellOperator();
        }
        setTextToCalculator(result);
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
