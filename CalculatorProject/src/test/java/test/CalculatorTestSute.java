package test;

import org.junit.Test;

import calculator.Calculator;

import static org.junit.Assert.*;



public class CalculatorTestSute {

	@Test
    public void testSampleInput1() {
        Calculator calculator = new Calculator();
        calculator.setExpressionInput("add(1,2)");
        assertEquals("add(1,2) = 3 ", 3L, calculator.evaluate());
    }
	
	@Test
    public void testSampleInput2() {
        Calculator calculator = new Calculator();
        calculator.setExpressionInput("add(1, mult(2, 3))");
        assertEquals("add(1, mult(2, 3)) = 7 ", 7L, calculator.evaluate());
    }
	
	@Test
    public void testSampleInput3() {
        Calculator calculator = new Calculator();
        calculator.setExpressionInput("mult(add(2, 2), div(9, 3))");
        assertEquals("mult(add(2, 2), div(9, 3)) = 12 ", 12L, calculator.evaluate());
    }
	
	@Test
    public void testSampleInput4() {
        Calculator calculator = new Calculator();
        calculator.setExpressionInput("let(a, 5, add(a, a))");
        assertEquals("let(a, 5, add(a, a)) = 10 ", 10L, calculator.evaluate());
    }
	

	@Test
    public void testSampleInput5() {
        Calculator calculator = new Calculator();
        calculator.setExpressionInput("let(a, 5, let(b, mult(a, 10), add(b, a)))");
        assertEquals("let(a, 5, let(b, mult(a, 10), add(b, a))) = 55 ", 55L, calculator.evaluate());
    }
	

	@Test
    public void testSampleInput6() {
        Calculator calculator = new Calculator();
        calculator.setExpressionInput("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))");
        assertEquals("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)) = 40 ", 40L, calculator.evaluate());
    }
}
