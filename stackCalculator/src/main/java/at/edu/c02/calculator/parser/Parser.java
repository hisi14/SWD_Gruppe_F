package at.edu.c02.calculator.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

import at.edu.c02.calculator.Calculator;
import at.edu.c02.calculator.CalculatorException;
import at.edu.c02.calculator.Calculator.Operation;

public class Parser {

	private Calculator calc_;

	public Parser(Calculator cal) {
		if (cal == null)
			throw new IllegalArgumentException("Calculator not set");
		calc_ = cal;
	}

	public double parse(File calculation) throws FileNotFoundException,
			XMLStreamException, CalculatorException {

		double result = 0;
		XMLEventReader r = createXmlEventReader(calculation);

		while (r.hasNext()) {
			XMLEvent e = r.nextEvent();
			Attribute attribute = e.asStartElement().getAttributeByName(
					new QName("value"));
			String value = attribute != null ? attribute.getValue() : "";
			if ("push".equals(e.asStartElement().getName().getLocalPart())) {
				if ("Result".equalsIgnoreCase(value)) {
					calc_.push(result);
				} else {
					calc_.push(Double.parseDouble(value));
				}
			} else if ("pop"
					.equals(e.asStartElement().getName().getLocalPart())) {
				calc_.pop();
			} else if ("operation".equals(e.asStartElement().getName()
					.getLocalPart())) {
				result = calc_.perform(readOperation(value));
			}
		}

		return result;
	}

	private XMLEventReader createXmlEventReader(File calculation)
			throws FactoryConfigurationError, FileNotFoundException,
			XMLStreamException {
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		FileReader fr = new FileReader(calculation);
		XMLEventReader xmler = xmlif.createXMLEventReader(fr);
		EventFilter filter = new EventFilter() {
			public boolean accept(XMLEvent event) {

				return event.isStartElement();
			}
		};

		XMLEventReader r = xmlif.createFilteredReader(xmler, filter);
		return r;
	}

	private Operation readOperation(String value) throws CalculatorException {

		if ("*".equals(value))
			return Operation.mul;
		else if ("+".equals(value))
			return Operation.add;
		else if ("/".equals(value))
			return Operation.div;
		else if ("-".equals(value))
			return Operation.sub;
		else if ("%".equals(value))
			return Operation.mod;
		
		throw new CalculatorException("Unsuppoted Operation");
	}
}
