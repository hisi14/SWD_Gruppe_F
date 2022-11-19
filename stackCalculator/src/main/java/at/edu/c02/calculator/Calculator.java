package at.edu.c02.calculator;

//test

public interface Calculator {

	enum Operation {
		add, sub, mul, div, mod, sin, cos, dotproduct
	};

	void push(double value);
	
	double pop() throws CalculatorException;
	
	double perform(Operation op) throws CalculatorException;
	
	void clear(); 
}
