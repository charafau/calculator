package com.nullpointerbay.calculator;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.BiFunction;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RpnCalc {

    public static final String OPERATION_ADD = "+";
    public static final String OPERATION_SUBTRACT = "-";

    /**
     * using Shunting-yard algorithm for tranforming infix notation
     * to postifx RPN notation
     *
     * @param input
     * @return
     */
    public String transformInfixToPostfix(String input) {

        List<String> outputQueue = new LinkedList<>();

        Stack<Operation> operationStack = new Stack<>();
        final String[] splitedEquation = input.split(" ");
        for (String token : splitedEquation) {
            if (isOperator(token)) {
                OperatorParser operatorParser = new OperatorParser(outputQueue, operationStack,
                        token).invoke();
                outputQueue = operatorParser.getOutputQueue();
                operationStack = operatorParser.getOperationStack();
            } else {
                outputQueue = addImmutable(outputQueue, token);
            }
        }

        outputQueue = addOperationsToOutputQueue(outputQueue, operationStack);

        return Stream.of(outputQueue)
                .collect(Collectors.joining(" "));
    }

    private List<String> addOperationsToOutputQueue(List<String> outputQueue, Stack<Operation> operationStack) {
        while (!operationStack.isEmpty()) {
            outputQueue = addImmutable(outputQueue, operationStack.pop().value());
        }
        return outputQueue;
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
    Stack<Operation> addOperation(Stack<Operation> operationStack, String token) {

        final Stack<Operation> newStack = new Stack<>();
        newStack.addAll(operationStack);

        newStack.add(Operation.formString(token));

        return newStack;
    }

    boolean isOperator(String token) {
        return Operation.formString(token) != Operation.UNKNOWN_OPERATION;
    }

    public double calculate(String postfixNotationEquation) {

        Stack<Double> output = new Stack<>();


        Stream.of(postfixNotationEquation.split(" ")).forEach(token -> {
            switch (token) {
                case OPERATION_ADD:
                    //must be final so no immutable stack :(
                    calculateOperation(output, (a, b) -> b + a);
                    break;
                case OPERATION_SUBTRACT:
                    calculateOperation(output, (a, b) -> b - a);
                    break;
                default:
                    output.push(Double.parseDouble(token));
            }
        });

        return output.pop();

    }

    private void calculateOperation(Stack<Double> output, BiFunction<Double, Double, Double> operation) {
        output.push(operation.apply(output.pop(), output.pop()));
    }


    private class OperatorParser {
        private List<String> outputQueue;
        private Stack<Operation> operationStack;
        private String token;

        OperatorParser(List<String> outputQueue, Stack<Operation> operationStack, String token) {
            this.outputQueue = outputQueue;
            this.operationStack = operationStack;
            this.token = token;
        }

        List<String> getOutputQueue() {
            return outputQueue;
        }

        Stack<Operation> getOperationStack() {
            return operationStack;
        }

        OperatorParser invoke() {
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
            return this;
        }
    }
}