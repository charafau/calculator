package com.nullpointerbay.calculator;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class RpnCalcTest {


    private RpnCalc rpnCalc;

    @Before
    public void setUp() throws Exception {
        rpnCalc = new RpnCalc();
    }

    @Test
    public void shouldTransformTwoNumbers() throws Exception {

        String input = "2 + 3";

        assertEquals("2 3 +", rpnCalc.transformInfixToPostfix(input));

    }

    @Test
    public void shouldTransformFourNumbers() throws Exception {

        String input = "2 + 3 + 4 - 5";

        assertEquals("2 3 + 4 + 5 -", rpnCalc.transformInfixToPostfix(input));

    }

    @Test
    public void shouldTransformSixNumbers() throws Exception {

        String input = "-2 + 3 - 4 + 5 + 2 + 2";

        assertEquals("-2 3 + 4 - 5 + 2 + 2 +", rpnCalc.transformInfixToPostfix(input));

    }

    @Test
    public void shouldMarkPlusAsOperator() throws Exception {
        assertTrue(rpnCalc.isOperator("+"));
    }

    @Test
    public void shouldMarkMinusAsOperator() throws Exception {
        assertTrue(rpnCalc.isOperator("-"));
    }

    @Test
    public void shouldReturnFalseForNumber() throws Exception {
        assertFalse(rpnCalc.isOperator("3"));
    }

    @Test
    public void shouldMakeStackImmutable() throws Exception {

        final Stack<Operation> oldOperations = new Stack<>();
        oldOperations.add(Operation.ADD);

        final Stack<Operation> newOperations = new Stack<>();
        newOperations.add(Operation.ADD);
        newOperations.add(Operation.SUBTRACT);

        final Stack<Operation> immutableOperations = new Stack<>();
        immutableOperations.add(Operation.ADD);

        assertThat(rpnCalc.addOperation(oldOperations, "-"), is(newOperations));
        assertThat(oldOperations, is(immutableOperations));

    }

    @Test
    public void shouldAddTwoNumbersInPostfix() throws Exception {

        double value = rpnCalc.calculate("1 2 +");

        assertThat(value, is(3.0));


    }

    @Test
    public void shouldCalculateFourNumbers() throws Exception {

        double value = rpnCalc.calculate("2 3 + 4 + 5 -");

        assertThat(value, is(4.0));

    }

    @Test
    public void shouldSupportMinusFirstEquations() throws Exception {

        final double value = rpnCalc.calculate("-2 3 +");

        assertThat(value, is(1.0));

    }
}