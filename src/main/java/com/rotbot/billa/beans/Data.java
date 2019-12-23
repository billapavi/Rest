package com.rotbot.billa.beans;

public class Data {
	private String[][] candles;

    public String[][] getCandles ()
    {
        return candles;
    }

    public void setCandles (String[][] candles)
    {
        this.candles = candles;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [candles = "+candles+"]";
    }
}
