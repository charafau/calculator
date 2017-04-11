package com.nullpointerbay.calculator;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RpnCalc {


    private static final String CHAR_PLUS = "+";
    private static final String CHAR_MINUS = "-";

    /**
     * using Shunting-yard algorithm for tranforming infix notation
     * to postifx RPN notation
     *
     * @param input
     * @return
     */
    public String transfortInfixToPostfix(String input) {


        List<String> outputQueue = new LinkedList<>();
        Stack<Operation> operationStack = new Stack<>();

        final String[] splitedEquation = input.split(" ");

        for (String token : splitedEquation) {
            if (isOperator(token)) {
                if (operationStack.isEmpty()) {
                    operationStack = addOperation(operationStack, token);
                } else {
                    if (OperationCalculatorToken.getOperatorLevel(operationStack.peek()) >
                            OperationCalculatorToken.getOperatorLevel(Operation.formString(token))) {

                        operationStack = addOperation(operationStack, token);
                    } else {
                        outputQueue = addImmutable(outputQueue, operationStack.pop().value());
                        operationStack = addOperation(operationStack, token);
                    }
                }
            } else {
                outputQueue = addImmutable(outputQueue, token);
            }
        }

        while (!operationStack.isEmpty()) {
            outputQueue = addImmutable(outputQueue, operationStack.pop().value());
        }

        return Stream.of(outputQueue)
                .collect(Collectors.joining(" "));
    }

    private List<String> addImmutable(List<String> outputQueue, String token) {
        final LinkedList<String> newList = new LinkedList<>();
        newList.addAll(outputQueue);
        newList.add(token);
        return newList;
    }

    /**
     * everyone wishes about java's immutable collections...
     *
     * @param operationStack
     * @param token
     * @return
     */
    private Stack<Operation> addOperation(Stack<Operation> operationStack, String token) {

        final Stack<Operation> newStack = new Stack<>();
        newStack.addAll(operationStack);

        if (CHAR_PLUS.equals(token)) {
            newStack.add(Operation.ADD);
        } else {
            newStack.add(Operation.SUBTRACT);
        }

        return newStack;
    }

    boolean isOperator(String token) {
        return CHAR_PLUS.equals(token) || CHAR_MINUS.equals(token);
    }
}