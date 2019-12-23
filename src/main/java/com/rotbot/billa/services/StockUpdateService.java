package com.rotbot.billa.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotbot.billa.beans.BasicStatisSticks;
import com.rotbot.billa.beans.Candle;
import com.rotbot.billa.repositories.MasterTableRepo;
import com.rotbot.billa.stocks.MainStockID;
import com.rotbot.billa.stocks.MasterStockTable;
import com.rotbot.billa.utils.PercentageCalulator;
import com.rotbot.billa.utils.TestUtilites;

@Service
public class StockUpdateService {

	@Autowired
	public MasterTableRepo masterStockTableRepo;
	
	public List<MasterStockTable> findGapUporDown(String date,String exchange,String up)
	{


		List<MasterStockTable> gapuplist = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar prevday = Calendar.getInstance();
		Date d = null;
		try {
			d = format.parse(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("format failed...");
			e.printStackTrace();
		}
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
		SimpleDateFormat prevDayFormat = new SimpleDateFormat("yyyy-MM-dd"); // the day of the week abbreviated
		System.out.println(simpleDateformat.format(d));

		if (simpleDateformat.format(d).equalsIgnoreCase("sat") || simpleDateformat.format(d).equalsIgnoreCase("sun")) {
			System.out.println("its a weekend so dont work");
		} else {
			// Write logic to find previous day...
			prevday.setTime(d);
			if (simpleDateformat.format(d).equalsIgnoreCase("Mon")) {
				prevday.add(Calendar.DATE, -2);
			} else {
				prevday.add(Calendar.DATE, -1);
			}
			Date pevDate = prevday.getTime();
			System.out.println("prev day is " + pevDate.getDay());
			System.out.println("wow its a week day we can work it out ....");

			prevDayFormat.format(pevDate);

			if (up.equalsIgnoreCase("up")) {
				// find the day candle is available or not
				// if available parse those days
				Arrays.stream(TestUtilites.foList).forEach(stockName -> {
					System.out.println(stockName + " checking for");
					Date da;
					try {
						da = format.parse(date);

						Optional<MasterStockTable> prev = masterStockTableRepo.findById(new MainStockID(
								prevDayFormat.format(pevDate) + "T00:00:00+0530", stockName.toUpperCase()));
						Optional<MasterStockTable> curr = masterStockTableRepo.findById(
								new MainStockID(prevDayFormat.format(da) + "T00:00:00+0530", stockName.toUpperCase()));
						if ((prev.get().getClose() < curr.get().getOpen()) && PercentageCalulator
								.getPercentage_4_digits(curr.get().getOpen(), prev.get().getOpen()) >= 1) {
							System.out.println("gap up found...." + stockName);
							System.out.println("gap Previous...." + prev.get().getClose().toString());
							System.out.println("gap Current...." + curr.get().getOpen().toString());
							gapuplist.add(curr.get());
//							gapuplist.put(stockName, PercentageCalulator.getPercentage_4_digits(curr.get().getOpen(),
//									prev.get().getOpen()));
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			} else {
				Arrays.stream(TestUtilites.foList).forEach(stockName -> {
					Date da;
					System.out.println(stockName + " checking for");
					try {
						da = format.parse(date);
						Optional<MasterStockTable> prev = masterStockTableRepo.findById(new MainStockID(
								prevDayFormat.format(pevDate) + "T00:00:00+0530", stockName.toUpperCase()));
						Optional<MasterStockTable> curr = masterStockTableRepo.findById(
								new MainStockID(prevDayFormat.format(da) + "T00:00:00+0530", stockName.toUpperCase()));
						if ((prev.get().getClose() > curr.get().getOpen()) && PercentageCalulator
								.getPercentage_4_digits(curr.get().getOpen(), prev.get().getOpen()) >= 1) {
							System.out.println("gap down found...." + stockName);
//							gapuplist.put(stockName, PercentageCalulator.getPercentage_4_digits(curr.get().getOpen(),
//									prev.get().getOpen()));
							gapuplist.add(curr.get());
							System.out.println("gap Previous...." + prev.get().toString());
							System.out.println("gap Current...." + curr.get().toString());
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});

			}
		}
		System.out.println("gap up or dwn list " + gapuplist);
		return gapuplist;
	}
	public List<MasterStockTable> findDayCandleSortedList(String date,String stockName)
	{
		// Find range of Day candles first
		String year = date.split("-")[0];
		List<MasterStockTable> prev = masterStockTableRepo.findByIdStockNameAndIdTimeStampLike(stockName.toUpperCase(), year+"%");
		System.out.println("range of stocks :"+prev);
		//2019-01-01T00:00:00+0530
		List<MasterStockTable> sortedList = prev.stream().sorted((a,b)-> {
			
			 String sDate1=a.getId().getTimeStamp().split("T")[0];
			 String sDate2=b.getId().getTimeStamp().split("T")[0];
			    Date date1;
			    Date date2;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
					date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
					
					return date2.compareTo(date1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			return 0;
		}).collect(Collectors.toList());
				
		sortedList.forEach(k->{
			System.out.println(k.getId().getTimeStamp());
		});
		return sortedList;
	}
	public Map<String, List<MasterStockTable>> findGapUpAndDownDayCandle(List<MasterStockTable> list)
	{
		Map<String,List<MasterStockTable>> updownlist = new HashMap<>();
		List<MasterStockTable> uplist = new ArrayList<>();
		List<MasterStockTable> downlist = new ArrayList<>();
		for (int i = 0; i < list.size()-1; i++) {
			// check for gap up condition..
			MasterStockTable curr = list.get(i);
			MasterStockTable prev = list.get(i+1);
			
			if(PercentageCalulator.getPercentage_4_digits(curr.getOpen(), prev.getClose())>1)
			{
				//gap up
				if(curr.getOpen()>prev.getClose())
				{
					uplist.add(curr);
					System.out.println("up added "+curr);
				}
				else
				{
					System.out.println("down added "+curr);
					downlist.add(curr);
				}
			}
			
		}
		updownlist.put("up", uplist);
		updownlist.put("down", downlist);
		return updownlist;
	}
	public void processBasicStrategy(String date,String stockName)
	{
		List<BasicStatisSticks> stats = new ArrayList<>();
		Map<String, List<MasterStockTable>> updownlist = findGapUpAndDownDayCandle(findDayCandleSortedList(date,stockName));
		updownlist.forEach((k,v)->{
			
			v.forEach(a ->{
				BasicStatisSticks bstats = new BasicStatisSticks();
				Candle c = new Candle(a.getId().getStockName(), a.getId().getTimeStamp(), Double.toString(a.getOpen()), Double.toString(a.getHigh()), Double.toString(a.getLow()), Double.toString(a.getClose()), Long.toString(a.getVolueme()));
				
				bstats.setData(c);
				bstats.setDate(c.getTime());
				bstats.setGapUPorDown(k);
				if(c.getOpenPrice()>c.getClosePrice())
				{
					
					bstats.setGreen("green");
				}
				else
				{
					bstats.setGreen("red");
					
				}
				stats.add(bstats);
			});
		});
		
		stats.forEach(k ->{
			System.out.println(k.getDate().split("T")[0]+" " +k.getGapUPorDown() +" % "+k.getData().getLowtoHighPercentage() +"  "+k.getGreen());
		});
	}
}
