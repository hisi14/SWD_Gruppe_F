package at.edu.c02.calculator;

//test

public interface Calculator {

	enum Operation {
		add, sub, mul, div
	};

	void push(double value);
	
	double pop() throws CalculatorException;
	
	double perform(Operation op) throws CalculatorException;
	
	void clear(); 
}
