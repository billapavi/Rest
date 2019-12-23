package com.rotbot.billa.utils;



public class PercentageCalulator {
	public static final double BL_1 = 1;
	public static final double GT_1_LT_1_5 = 1.5;
	public static final double GT_1_5 = 1.8;
	public static final double GT_2 = 2;
	
	public volatile boolean hedgeFlag = false;
	public volatile boolean normalFlag = false;
	public volatile boolean rangeBoundFlag = false;
	
	public volatile boolean Bull_priceMovetTo_1 = false;
	public volatile boolean Bull_priceMovetTo_1_5 = false;
	public volatile boolean Bull_priceMovetTo_2 = false;
	public volatile boolean Bull_priceMovetTo_Lt_1 = false;
	
	public volatile boolean Bear_priceMovetTo_1 = false;
	public volatile boolean Bear_priceMovetTo_1_5 = false;
	public volatile boolean Bear_priceMovetTo_2 = false;
	public volatile boolean Bear_priceMovetTo_2_5 = false;
	
	public volatile double bullPercentage = 0;
	public volatile double bearPercentage = 0;
	public PercentageCalulator perceageCalc;
	
	public volatile boolean buyFlag = false;
	public volatile boolean sellFlag = false;
	public void normalStratagy()
	{
		if(!normalFlag)
		System.out.println("normal stratagy activated.. for");
		normalFlag = true;
		hedgeFlag = false;
		rangeBoundFlag = false;
	}
	public void HedgeStratagy()
	{
		if(!hedgeFlag)
		System.out.println("hedge stratagy activated");
		normalFlag = false;
		hedgeFlag = true;
		rangeBoundFlag = false;
	}
	public void rangeStratagy()
	{
		if(!rangeBoundFlag)
		System.out.println("range stratagy is actiated");
		normalFlag = false;
		hedgeFlag = false;
		rangeBoundFlag = true;
	}
	
	public static double getPercentage(double highlow,double currpostion)
	{
		double percentage = 0;
//		System.out.println("pecentage calculation "+highlow+" low "+currpostion);
		if((highlow-currpostion)==0)
		{
			return 0;
		}
		if(highlow> currpostion)
		{
			percentage = ((highlow-currpostion)/currpostion)*100;
		}
		else
		{
			percentage = ((currpostion-highlow)/currpostion)*100;
		}
		if(percentage==0)
		{
			return 0;
		}
		return PriceConverter.covertNumaricDouble(percentage);
	}
	public static double getPercentage_4_digits(double highlow,double currpostion)
	{
		double percentage = 0;
//		System.out.println("pecentage calculation "+highlow+" low "+currpostion);
		if(highlow> currpostion)
		{
			percentage = ((highlow-currpostion)/currpostion)*100;
		}
		else
		{
			percentage = ((currpostion-highlow)/currpostion)*100;
		}
		return PriceConverter.covertNumaricDouble_4_digits(percentage);
	}
}
