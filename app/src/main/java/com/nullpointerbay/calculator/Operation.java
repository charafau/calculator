package com.nullpointerbay.calculator;

import static com.nullpointerbay.calculator.RpnCalc.OPERATION_ADD;
import static com.nullpointerbay.calculator.RpnCalc.OPERATION_SUBTRACT;

public enum Operation {

    ADD(OPERATION_ADD),
    SUBTRACT(OPERATION_SUBTRACT),
    UNKNOWN_OPERATION("");

    private final String value;

    Operation(String value) {
        this.value = value;
    }

    public static  Operation formString(String value) {
        switch (value) {
            case OPERATION_ADD:
                return ADD;
            case OPERATION_SUBTRACT:
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
