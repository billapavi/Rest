package com.rotbot.billa.utils;


import java.text.DecimalFormat;

public class PriceConverter {

	public static String covertDouble(double d)
	{
		DecimalFormat formatter = new DecimalFormat("#.#");
		
		return formatter.format(d);
	}
	public static String covertInteger(double d)
	{
		DecimalFormat formatter = new DecimalFormat("#");
		
		return formatter.format(d);
	}
	public static Double covertNumaricDouble(double d)
	{
		DecimalFormat formatter = new DecimalFormat("#.#");
		
		return new Double(formatter.format(d));
	}
	public static Double covertNumaricDouble_4_digits(double d)
	{
		DecimalFormat formatter = new DecimalFormat("#.##");
		return new Double(formatter.format(d));
	}
//	public static void main(String[] args) {
//		String stringDouble = PriceConverter.covertDouble(100.02145d);
//		double normaldouble = PriceConverter.covertNumaricDouble(1000.234566d);
//		System.out.println(stringDouble+" string double");
//		System.out.println(normaldouble+" normal double");
//	}
}
