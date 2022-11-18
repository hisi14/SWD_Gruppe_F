package at.edu.c02.calculator;

import at.edu.c02.calculator.logic.CalculatorImpl;
import at.edu.c02.calculator.parser.Parser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.File;

public class EndToEndTest
{
    @Test
    public void testXml04() throws Exception {

        Calculator cal = new CalculatorImpl();
        Parser parser = new Parser(cal);
        double result = parser.parse(new File("src/test/resources/test04.xml"));

        assertEquals(5, result, 0);
    }

    @Test
    public void testXmlMod() throws Exception {

        Calculator cal = new CalculatorImpl();
        Parser parser = new Parser(cal);
        double result = parser.parse(new File("src/test/resources/modTest.xml"));

        assertEquals(3, result, 0);
    }

    @Test(expected = CalculatorException.class)
    public void testInvalidOperation() throws Exception {
            Calculator cal = new CalculatorImpl();
            Parser parser = new Parser(cal);
            double result = parser.parse(new File("src/test/resources/test05.xml"));

}

}
