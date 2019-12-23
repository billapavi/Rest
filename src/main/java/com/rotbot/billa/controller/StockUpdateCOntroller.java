package com.rotbot.billa.controller;

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

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.rotbot.billa.DataSeeder;
import com.rotbot.billa.Factory.StockFactory;
import com.rotbot.billa.beans.Candle;
import com.rotbot.billa.repositories.MasterTableMinDataRepo;
import com.rotbot.billa.repositories.MasterTableRepo;
import com.rotbot.billa.repositories.StockRepository;
import com.rotbot.billa.services.StockUpdateService;
import com.rotbot.billa.stocks.LUPIN;
import com.rotbot.billa.stocks.MainStockID;
import com.rotbot.billa.stocks.MasterStockTable;
import com.rotbot.billa.stocks.MasterStockTableMinData;
import com.rotbot.billa.stocks.SuperStock;
import com.rotbot.billa.utils.PercentageCalulator;
import com.rotbot.billa.utils.TestUtilites;

@RestController
public class StockUpdateCOntroller {

	@Autowired
	public StockRepository stockRepo;
	@Autowired
	public MasterTableRepo masterStockTableRepo;
	@Autowired
	public MasterTableMinDataRepo masterTableMinDataRepo;

	@Autowired
	public EntityManager entityManager;
	
	@Autowired
	public StockUpdateService stockUpdateService;

	@RequestMapping(path = "/find/{up}/{exchange}/{date}")
	public Map<String, Double> updateStocks(@PathVariable String up, @PathVariable String exchange,
			@PathVariable String date) {
		System.out.println("up downs called.." + up + " " + exchange + " " + date);
		Gson g = new Gson();
		String s = g.toJson(gapUpDownsExchange(up, exchange, date));
		return gapUpDownsExchange(up, exchange, date);
	}

	private Map<String, Double> gapUpDownsExchange(String up, String exchange, String date) {

		Map<String, Double> gapuplist = new HashMap<>();
		// find first day and prev day Data to find
		// check if current day is a week day or not....

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

							gapuplist.put(stockName, PercentageCalulator.getPercentage_4_digits(curr.get().getOpen(),
									prev.get().getOpen()));
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
							gapuplist.put(stockName, PercentageCalulator.getPercentage_4_digits(curr.get().getOpen(),
									prev.get().getOpen()));
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


	@RequestMapping(path = "/update/{exchange}/{dayormin}")
	public String FindGapDowns(@PathVariable String exchange, @PathVariable String dayormin) {
		// Check if Nify_fifty or FO stocks
		process(exchange, dayormin);

		return "";
	}
	
	@RequestMapping(path = "/update/{exchange}/{dayormin}/{from}/{to}")
	public String saveOrUpdateCandleDataToDB(@PathVariable String exchange, @PathVariable String dayormin,@PathVariable String from,@PathVariable String to) {
		// Check if Nify_fifty or FO stocks
		process(exchange, dayormin,from,to);
		
		return "";
	}

	private void process(String exchange, String dayormin, String from, String to) {
		// TODO Auto-generated method stub
		if (exchange.equalsIgnoreCase("FO")) {
			Arrays.stream(TestUtilites.foList).forEach(k -> {
				System.out.println(k + " processing ");
				if (DataSeeder.stockNameValues.get(k) != null) {
					executeLogic(from, to, k, dayormin);
				}
			});
		}else 
		{
			Arrays.stream(TestUtilites.NIFTY_FIFTY).forEach(k -> {
				System.out.println(k + " processing ");
				if (DataSeeder.stockNameValues.get(k) != null) {
					executeLogic(from, to, k, dayormin);
				}
			});
		}
	}

	@RequestMapping(path = "/update/{stockName}/{from}/{to}")
	public String updateINDStocks(@PathVariable String stockName, @PathVariable String from, @PathVariable String to) {
		// Check if Nify_fifty or FO stocks
		executeLogic(to, from, stockName, "minute");
		System.out.println(stockName + " " + from + " " + to);

		return "";
	}

	private void process(String stockName, String from, String to) {
		// TODO Auto-generated method stub

	}

	private void process(String exchange, String dayormin) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String currDate = format.format(new Date());
		String currDate = "2019-05-17";
		String prevDate = "2019-01-01";

		if (exchange.equalsIgnoreCase("FO")) {
			Arrays.stream(TestUtilites.foList).forEach(k -> {
				System.out.println(k + " processing ");
				if (DataSeeder.stockNameValues.get(k) != null) {
					executeLogic(currDate, prevDate, k, dayormin);
				}
			});
		} else {
			Arrays.stream(TestUtilites.NIFTY_FIFTY).forEach(k -> {

				System.out.println(k + " processing ");
				if (DataSeeder.stockNameValues.get(k) != null) {
					executeLogic(currDate, prevDate, k, dayormin);
				}
			});

		}
	}

	/**
	 * 
	 * @param currDate
	 * @param prevDate
	 * @param k
	 * @param dayormin "minute"/"day"
	 */
	private void executeLogic(String currDate, String prevDate, String k, String dayormin) {
		Map<String, List<Candle>> ss = TestUtilites.createMinDataOneDay(DataSeeder.stockNameValues.get(k), prevDate,
				currDate, dayormin, "");
		if (dayormin.equalsIgnoreCase("day")) {
			saveMasterTableData(ss, k);
		} else {
			saveMasterTableMinData(ss, k);

		}
	}

	private void Save(Map<String, List<Candle>> ss, String stname) {
		List<SuperStock> sList = new ArrayList<>();

		ss.get(DataSeeder.stockNameValues.get(stname)).forEach(candle -> {
			if (candle != null) {

				SuperStock s = StockFactory.getStock(stname);
				s.setOpen(candle.getOpenPrice());
				s.setHigh(candle.getHighPrice());
				s.setLow(candle.getLowPrice());
				s.setClose(candle.getClosePrice());
				s.setVolueme(candle.getVolumePrice());
				s.setStockName(stname);
				s.setTimestamp(candle.getTime());
				s.setTokenID(candle.getName());
				sList.add(s);
			} else {
				System.out.println("candle is null billa");
			}

		});
		stockRepo.saveAll(sList);

	}

	private void saveMasterTableData(Map<String, List<Candle>> ss, String stname) {
		List<MasterStockTable> sList = new ArrayList<>();

		ss.get(DataSeeder.stockNameValues.get(stname)).forEach(candle -> {
			if (candle != null) {

				MasterStockTable s = new MasterStockTable();
				s.setOpen(candle.getOpenPrice());
				s.setHigh(candle.getHighPrice());
				s.setLow(candle.getLowPrice());
				s.setClose(candle.getClosePrice());
				s.setVolueme(candle.getVolumePrice());
				s.setId(new MainStockID(candle.getTime(), stname));
				s.setTokenID(candle.getName());
				sList.add(s);
			} else {
				System.out.println("candle is null billa");
			}

		});
		masterStockTableRepo.saveAll(sList);

	}

	private void saveMasterTableMinData(Map<String, List<Candle>> ss, String stname) {
		List<MasterStockTableMinData> sList = new ArrayList<>();

		ss.get(DataSeeder.stockNameValues.get(stname)).forEach(candle -> {
			if (candle != null) {

				MasterStockTableMinData s = new MasterStockTableMinData();
				s.setOpen(candle.getOpenPrice());
				s.setHigh(candle.getHighPrice());
				s.setLow(candle.getLowPrice());
				s.setClose(candle.getClosePrice());
				s.setVolueme(candle.getVolumePrice());
				s.setId(new MainStockID(candle.getTime(), stname));
				s.setTokenID(candle.getName());
				sList.add(s);
			} else {
				System.out.println("candle is null billa");
			}

		});
		masterTableMinDataRepo.saveAll(sList);
	}

	@RequestMapping(path = "/get/{stockname}")
	private List<MasterStockTableMinData> getMinDatat(@PathVariable String stockname) {

		List<MasterStockTableMinData> sList = new ArrayList<>();
		System.out.println("get called");
 
//		return masterTableMinDataRepo.findByIdStockName(stockname.toUpperCase());
//		return masterTableMinDataRepo.findByIdLike(new MainStockID("2019-01-01T0%", stockname.toUpperCase()));
		return masterTableMinDataRepo.findByIdStockNameAndIdTimeStampLike(stockname.toUpperCase(), "2019-01-01%");
	}
	@RequestMapping(path = "/statisTicks/{date}/{stockname}")
	private List<MasterStockTableMinData> basicStrategy(@PathVariable String date,@PathVariable String stockname) {
		
		List<MasterStockTableMinData> sList = new ArrayList<>();
		System.out.println("get called /statisTicks/{date}/{stockname}");
		
//		return masterTableMinDataRepo.findByIdStockName(stockname.toUpperCase());
//		return masterTableMinDataRepo.findByIdLike(new MainStockID("2019-01-01T0%", stockname.toUpperCase()));
		stockUpdateService.processBasicStrategy(date, stockname);
		return null;
	}
	
	@RequestMapping(path = "/ss")
	private String ss() {
		
		return "ha ";
	}
}
