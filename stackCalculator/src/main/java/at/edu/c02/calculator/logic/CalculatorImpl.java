package at.edu.c02.calculator.logic;

import java.util.Stack;

import at.edu.c02.calculator.Calculator;
import at.edu.c02.calculator.CalculatorException;


public class CalculatorImpl implements Calculator
{

    private Stack<Double> stack_ = new Stack<Double>();

    @Override
    public double perform(Operation op) throws CalculatorException
    {

        double b = pop();
        double a = 0;

        if(!op.equals(Operation.sin) && !op.equals(Operation.cos))
        {
            a = pop();
        }
        double radians;

        switch (op)
        {
            case add:
                return a + b;
            case sub:
                return a - b;
            case div:
                double c = a / b;
                if (Double.isInfinite(c))
                {
                    throw new CalculatorException("Division by zero");
                }
                return c;
            case mul:
                return a * b;
            case mod:
                if (b == 0)
                {
                    throw new CalculatorException("Division by zero");
                }
                return a % b;

            case sin:
                radians = Math.toRadians(b);
                return Math.round(Math.sin(radians)*1000) / 1000.0;

            case cos:
                radians = Math.toRadians(b);
                return Math.round(Math.cos(radians)*1000) / 1000.0;

            case dotproduct:


        }
        return 0;
    }

    @Override
    public double pop() throws CalculatorException
    {
        if (stack_.isEmpty())
        {
            throw new CalculatorException();
        }
        return stack_.pop();
    }

    @Override
    public void push(double v)
    {
        stack_.push(v);
    }

    @Override
    public void clear()
    {
        stack_.clear();
    }

}
