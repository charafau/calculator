package com.nullpointerbay.calculator;

public class NumberCalculatorToken implements CalculatorToken {

    private final String value;

    public NumberCalculatorToken(String value) {
        this.value = value;
    }

    @Override
    public boolean isOperation() {
        return false;
    }

    @Override
    public String getValue() {
        return value;
    }
}
