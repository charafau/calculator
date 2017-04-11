package com.nullpointerbay.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

        assertEquals("2 3 +", rpnCalc.transfortInfixToPostfix(input));

    }

    @Test
    public void shouldTransformFourNumbers() throws Exception {

        String input = " 2 + 3 + 4 - 5";

        assertEquals("2 3 + 4 + 5 -", rpnCalc.transfortInfixToPostfix(input));

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
}