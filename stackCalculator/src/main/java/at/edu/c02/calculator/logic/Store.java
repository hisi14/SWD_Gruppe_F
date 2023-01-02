package at.edu.c02.calculator.logic;

import at.edu.c02.calculator.CalculatorException;

import java.util.HashMap;

public class Store
{
    private HashMap<String,Double> storeLoadHash;

    public Store()
    {
        storeLoadHash = new HashMap<>();
    }

    public void store(String key, double result)
    {
        storeLoadHash.put(key,result);
    }

    public double load(String key) throws CalculatorException
    {
       double returnValue;
       try
       {
           returnValue = storeLoadHash.get(key);
       }
       catch(NullPointerException e)
        {
           throw new CalculatorException();
       }
        return returnValue;
    }
}
