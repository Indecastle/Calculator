package com.example.calculator;

public class CalculatorModel {

    private static final CalculatorModel INSTANCE = new CalculatorModel();

    private CalculatorModel() {
        savedOperator = null;
        savedOperation = null;
        memory = 0;
    }

    public static CalculatorModel getInstance() {
        return INSTANCE;
    }

    private Double savedOperator;
    private String savedOperation;
    private double memory;

    public void setOperator(String operator) {

        double currentOperator = Double.valueOf(operator);
        if(savedOperator != null){
            savedOperator = currentOperator;
        }
    }
    public void dellOperator() {
        savedOperator = null;
        savedOperation = null;
    }

    public String getResultOfOperation(String currentOperation, double currentOperator) {
        String result;

        switch(currentOperation) {
            case "sqrt":
                result = getSqrt(currentOperator);
                break;
            case "sqrt3":
                result = getPow(currentOperator, 1.0 / 3);
                break;
            case "squared":
                result = getPow(currentOperator, 2);
                break;
            case "cube":
                result = getPow(currentOperator, 3);
                break;
            case "exp":
                result = getExp(currentOperator);
                break;
            case "sin":
                result = getSin(currentOperator);
                break;
            case "cos":
                result = getCos(currentOperator);
                break;
            case "tan":
                result = getTan(currentOperator);
                break;
            case "log":
                result = getLog(currentOperator);
                break;
            case "lg":
                result = getLg(currentOperator);
                break;
            case "ten":
                result = getDegreeOfTen(currentOperator);
                break;
            case "mPlus":
                result = addToMemory(currentOperator);
                break;
            default:
                result = getResultOfBinaryOperatoin(currentOperation, currentOperator);

        }
        return result;
    }

    // for showing memory because there are no operators
    public String getResultOfOperation(String currentOperation) {
        return showMemory();
    }

    public String getResultOfBinaryOperatoin(String currentOperation, double currentOperator) {
        String result = "";
        if ((savedOperation == null) & (savedOperator == null) & (currentOperation != "=")) {
            savedOperation = currentOperation;
            savedOperator = currentOperator;
            result = deletePointZeroIfExist(String.valueOf(savedOperator));
        }
        else if ((savedOperation == null) & (savedOperator == null) & (currentOperation == "=")) {
            result = deletePointZeroIfExist(String.valueOf(currentOperator));
        }
        else if ((savedOperation != null) & (savedOperator != null) & (currentOperation != "=")) {
            if (!isRightExpresion(currentOperator)) {
                savedOperator = null;
                savedOperation = null;
                result = "Error";
            }
            else {
                savedOperator = solveBinaryExpr(savedOperator, currentOperator, savedOperation);
                savedOperation = currentOperation;
                result = deletePointZeroIfExist(String.valueOf(savedOperator));
            }

        }
        else if ((savedOperation != null) & (savedOperator != null) & (currentOperation == "=")) {
            if (!isRightExpresion(currentOperator)) {
                savedOperator = null;
                savedOperation = null;
                result = "Error";
            }
            else {
                result = deletePointZeroIfExist(String.valueOf(solveBinaryExpr(savedOperator, currentOperator, savedOperation)));
                savedOperator = null;
                savedOperation = null;
            }
        }

        return result;
    }

    private boolean isRightExpresion(double currentOperator) {
        boolean isRight = true;

        if (((savedOperation == "/") & (currentOperator == 0)) |
            ((savedOperation == "anyDegree") & (savedOperator < 0) & (Math.pow(currentOperator, -1) % 2 == 0) & (currentOperator < 1))) {
            isRight = false;
        }

        return isRight;
    }

    private double solveBinaryExpr(double firstOperator, double secondOperator, String operation) {
        switch(operation){
            case "/": firstOperator = firstOperator / secondOperator;
            break;
            case "*": firstOperator = firstOperator * secondOperator;
            break;
            case "-": firstOperator = firstOperator - secondOperator;
            break;
            case "+": firstOperator = firstOperator + secondOperator;
            break;
            case "anyDegree": firstOperator = Double.valueOf(getPow(firstOperator, secondOperator));
            break;

        }

        return firstOperator;
    }

    private String getSqrt(double operator) {
        return deletePointZeroIfExist((operator > 0) ? String.valueOf(Math.pow(operator, 0.5)) : "Error");
    }

    private String getPow(double operator, double pow) {
        String result = ((operator < 0) & (pow % 2 != 0)) ? String.valueOf((-1) * Math.pow(operator * (-1), pow)) : String.valueOf(Math.pow(operator, pow));
        return deletePointZeroIfExist(result);
    }

    private String getExp(double operator) {
        return deletePointZeroIfExist(String.valueOf(Math.exp(operator)));
    }

    private String getSin(double rad) {
        return deletePointZeroIfExist(String.valueOf(Math.sin(rad)));
    }

    private String getCos(double rad) {
        return deletePointZeroIfExist(String.valueOf(Math.cos(rad)));
    }

    private String getTan(double rad) {
        return deletePointZeroIfExist(String.valueOf(Math.tan(rad)));
    }

    private String getLog(double operator) {
        String result = operator > 0 ? String.valueOf(Math.log(operator)) : "Error";
        return deletePointZeroIfExist(result);
    }

    private String getLg(double operator) {
        String result = operator > 0 ? String.valueOf(Math.log10(operator)) : "Error";
        return deletePointZeroIfExist(result);
    }

    private String getDegreeOfTen(double operator) {
        return deletePointZeroIfExist(String.valueOf(Math.pow(10, operator)));
    }

    private String addToMemory(double operator) {
        memory += operator;
        return deletePointZeroIfExist(String.valueOf(operator));
    }

    private String showMemory() {
        return deletePointZeroIfExist(String.valueOf(memory));
    }

    public void clearMemory() {
        memory = 0;
    }

    // if the number ends with .0 then delete this part
    private String deletePointZeroIfExist(String string) {
        if (string.length() > 2) {
            string = (string.charAt(string.length() - 1) == '0' & (string.charAt(string.length() - 2) == '.')) ? String.valueOf((new StringBuilder(string)).delete(string.length() - 2, string.length())) : string;
        }

        return string;
    }
}
