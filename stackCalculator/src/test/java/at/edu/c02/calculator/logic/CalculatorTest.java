package at.edu.c02.calculator.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import at.edu.c02.calculator.Calculator;
import at.edu.c02.calculator.CalculatorException;
import at.edu.c02.calculator.Calculator.Operation;
import at.edu.c02.calculator.logic.CalculatorImpl;

public class CalculatorTest
{

    @Test
    public void testStoreAndLoad() throws CalculatorException
    {
        //setup
        Calculator calc = new CalculatorImpl();

        //execute
        calc.push(2.0);
        calc.push(3);
        double result = calc.perform(Operation.add);
        calc.store(result);
        calc.push(4);
        calc.push(result);
        result = calc.perform(Operation.add);
        calc.push(result);
        result = calc.load();
        calc.push(result);
        result = calc.perform(Operation.mul);

        //verify
        assertEquals(45, result, 0);
    }

    @Test
    public void testSimpleAddOperation() throws Exception
    {

        //setup
        Calculator calc = new CalculatorImpl();

        //execute
        calc.push(2.0);
        calc.push(3);
        double result = calc.perform(Operation.add);

        //verify
        assertEquals(5, result, 0);

    }

    @Test
    public void testSimpleMulOperation() throws Exception
    {

        Calculator calc = new CalculatorImpl();
        calc.push(2.0);
        calc.push(3);
        double result = calc.perform(Operation.mul);

        assertEquals(6, result, 0);

    }

    @Test
    public void testSimpleDivOperation() throws Exception
    {

        Calculator calc = new CalculatorImpl();
        calc.push(6.0);
        calc.push(2);
        double result = calc.perform(Operation.div);

        assertEquals(3, result, 0);

    }

    @Test
    public void testSimpleModOperation() throws Exception
    {

        Calculator calc = new CalculatorImpl();
        calc.push(6.0);
        calc.push(2);
        double result = calc.perform(Operation.mod);

        assertEquals(0, result, 0);

    }

    @Test
    public void testSimpleSinOperation() throws Exception
    {

        Calculator calc = new CalculatorImpl();
        calc.push(30.0);
        double result = calc.perform(Operation.sin);

        assertEquals(0.5, result, 0);

    }

    @Test
    public void testSimpleCosOperation() throws Exception
    {

        Calculator calc = new CalculatorImpl();
        calc.push(90.0);
        double result = calc.perform(Operation.cos);

        assertEquals(0, result, 0);

    }

    @Test
    public void testScalarOperation() throws Exception
    {
        Calculator calc = new CalculatorImpl();
        calc.push(1.0);
        calc.push(3.0);
        calc.push(2.0);
        calc.push(4.0);
        calc.push(2.0);
        double result = calc.perform(Operation.dotproduct);
        assertEquals(14, result, 0);
    }

    @Test(expected = CalculatorException.class)
    public void testNegativeScalarOperation() throws Exception
    {
        Calculator calc = new CalculatorImpl();
        calc.push(1.0);
        calc.push(3.0);
        calc.push(2.0);
        calc.push(4.0);
        calc.push(0.0);
        calc.perform(Operation.dotproduct);
    }

    //
    @Test(expected = CalculatorException.class)
    public void testPopOnEmptyStack() throws Exception
    {

        Calculator calc = new CalculatorImpl();
        calc.pop();

    }

    //
    @Test(expected = CalculatorException.class)
    public void testModDivision() throws Exception
    {

        Calculator calc = new CalculatorImpl();
        calc.push(3);
        calc.push(0);
        calc.perform(Operation.mod);
    }

    @Test
    public void testDivisionByZero() throws Exception
    {

        //Setup
        Calculator calc = new CalculatorImpl();
        try
        {
            calc.push(2);
            calc.push(0);
            calc.perform(Operation.div);

            fail("Exception expected");

        }
        catch (CalculatorException e)
        {
            assertEquals("Division by zero", e.getMessage());
            // e.getCause()
        }

    }
}
