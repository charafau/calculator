package com.nullpointerbay.calculator;

import java.util.HashMap;
import java.util.Map;

public class OperationCalculatorToken implements CalculatorToken {

    private static Map<Operation, Integer> operationWeights = new HashMap<>();
    static {
        operationWeights.put(Operation.ADD, 1);
        operationWeights.put(Operation.SUBTRACT, 1);
    }

    private final Operation operation;

    public OperationCalculatorToken(Operation operation) {
        this.operation = operation;
    }

    public static int getOperatorLevel(Operation operation) {
        return operationWeights.get(operation);
    }

    @Override
    public boolean isOperation() {
        return true;
    }

    @Override
    public String getValue() {
        return operation.value();
    }


}
