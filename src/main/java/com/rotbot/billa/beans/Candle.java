package com.rotbot.billa.beans;

import com.rotbot.billa.utils.PercentageCalulator;

public class Candle {

	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private String open;

	public String getOpen() {
		return open;
	}

	public Double getOpenPrice() {
		return Double.parseDouble(open);
	}

	public Double getHighPrice() {
		return Double.parseDouble(high);
	}

	public Double getLowPrice() {
		return Double.parseDouble(low);
	}

	public Double getClosePrice() {
		return Double.parseDouble(close);
	}

	public long getVolumePrice() {
		return Long.parseLong(volume);
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	private String high;
	private String low;
	private String close;
	private String volume;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Candle() {
		this.name ="";
		this.time = "";
		this.open = "0";
		this.high = "0";
		this.low = "0";
		this.close = "0";
		this.volume = "0";
	}

	public Candle(String name, String time, String open, String high, String low, String close, String volume) {
		super();
		this.name = name;
		this.time = time;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "stockName " + name + " time :" + time + " open " + open + " high " + high + " low " + low + " close "
				+ close;
	}
	public double getLowtoHighPercentage()
	{
		return PercentageCalulator.getPercentage(getHighPrice(), getLowPrice());
	}
	
	public boolean checkIfOpeneqHigh()
	{
		return getOpenPrice()==getHighPrice();
	}
	public boolean checkIfOpeneqLow()
	{
		return getOpenPrice()==getLowPrice();
	}
	public void updateCandle(Candle c)
	{
		if(getOpenPrice()==0)
		{
			setOpen(c.getOpen());
		}
		if(getClosePrice()==0)
		{
			setClose(c.getClose());
		}
		if(c.getHighPrice()>getHighPrice()||getHighPrice()==0)
		{
			setHigh(c.getHigh());
		}
		if(c.getLowPrice()<getLowPrice()|| getLowPrice()==0)
		{
			setLow(c.getLow());
		}
		setClose(c.getClose());
	}
}
