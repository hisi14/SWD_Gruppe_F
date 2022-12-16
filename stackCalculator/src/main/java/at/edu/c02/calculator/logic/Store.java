package at.edu.c02.calculator.logic;

import java.util.HashMap;

public class Store
{
    private HashMap<String,Double> storeLoadHash;
    private CalculatorImpl cal;

    public Store()
    {
        storeLoadHash = new HashMap<>();
        cal = new CalculatorImpl();
    }

    public void store(String key, double result)
    {
        storeLoadHash.put(key,result);
    }

    public double load(String key)
    {
        return storeLoadHash.get(key);
    }
}
