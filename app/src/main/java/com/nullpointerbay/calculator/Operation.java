package com.nullpointerbay.calculator;

public enum Operation {
    ADD("+"),
    SUBTRACT("-"),
    UNKNOWN_OPERATION("");

    private final String value;

    Operation(String value) {
        this.value = value;
    }

    public static  Operation formString(String value) {
        switch (value) {
            case "+":
                return ADD;
            case "-":
                return SUBTRACT;
            default:
                return UNKNOWN_OPERATION;
        }
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
