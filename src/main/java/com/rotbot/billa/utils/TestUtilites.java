package com.rotbot.billa.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.rotbot.billa.beans.Candle;
import com.rotbot.billa.beans.MyPojo;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class TestUtilites {
	private static OkHttpClient client = new OkHttpClient();

	public static String[][] getUserData(String stockName, String interval, String DayORMIN, String from, String to) {
		String json;
		System.out.println("https://kitecharts.zerodha.com/api/chart/" + stockName + "/" + interval + DayORMIN
				+ "?public_token=4gP7h7czIGFyM3PxXrpq5TkNZgqyQonu&user_id=RP5337&api_key=kitefront&access_token=chFb7jjOrTreLF67tOF3hIiwsKWdhx5m&from="
				+ from + "&to=" + to + "&ciqrandom=1539504969016");
		json = getJson("https://kitecharts.zerodha.com/api/chart/" + stockName + "/" + interval + DayORMIN
				+ "?public_token=4gP7h7czIGFyM3PxXrpq5TkNZgqyQonu&user_id=RP5337&api_key=kitefront&access_token=chFb7jjOrTreLF67tOF3hIiwsKWdhx5m&from="
				+ from + "&to=" + to + "&ciqrandom=1539504969016");

		if (json != null) {

			Gson gson = new Gson();
			MyPojo pojo = gson.fromJson(json, MyPojo.class);
			if (pojo != null && pojo.getData() != null)
				return pojo.getData().getCandles();
		}
		return null;
	}

	public static String getJson(String url) {
		Request request = new Request.Builder().url(url).build();

		try {
			client.retryOnConnectionFailure();
			OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
			builder.connectTimeout(30, TimeUnit.SECONDS);
			builder.readTimeout(30, TimeUnit.SECONDS);
			builder.writeTimeout(30, TimeUnit.SECONDS);
			client = builder.build();
			okhttp3.Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, List<Candle>> createMinDataOneDay(String stockName, String from, String to,
			String DayORMIN, String interval) {
		Map<String, List<Candle>> list = new HashMap<>();
		{
			System.out.println(stockName);
			List<Candle> candles = new ArrayList<Candle>();
			String stname = stockName.replace("NSE:", "");
			String[][] data = getUserData(stockName, DayORMIN, "", from, to);
			String[] tableName = null;
			if (data != null) {
				String[] tempData = new String[] { "", "", "", "", "", "" };
				for (int row = 0; row < data.length; row++) {

					for (int col = 0; col < data[row].length; col++) {
						tempData[col] = data[row][col];
					}
					Candle candle = new Candle(stname, tempData[0], tempData[1], tempData[2], tempData[3], tempData[4],
							tempData[5]);
					candles.add(candle);
					System.out.println(candle);
					tableName = tempData[0].split("T");
					if (tableName[1].contains("15:29")) {

					}
				}
			}
			list.put(stname, candles);
			return list;

		}
	}

	public static final String[] foList = { "RCOM", "RPOWER", "RELINFRA", "DISHTV", "SREINFRA", "DHFL", "INFRATEL",
			"JUBLFOOD", "BRITANNIA", "IDEA", "RELCAPITAL", "NIITTECH", "GODFRYPHLP", "ONGC", "IGL", "NHPC", "ZEEL",
			"STAR", "MFSL", "GLENMARK", "JINDALSTEL", "MANAPPURAM", "OIL", "APOLLOTYRE", "TATACOMM", "TATAMOTORS",
			"AXISBANK", "CASTROLIND", "LICHSGFIN", "PETRONET", "TECHM", "VGUARD", "NTPC", "BATAINDIA", "TATACHEM",
			"NBCC", "PFC", "BHARATFIN", "UPL", "VEDL", "NMDC", "PEL", "TATAMTRDVR", "GMRINFRA", "NATIONALUM",
			"AMARAJABAT", "MUTHOOTFIN", "INDUSINDBK", "LT", "ICICIPRULI", "SRTRANSFIN", "JETAIRWAYS", "CHENNPETRO",
			"AJANTPHARM", "CIPLA", "BEL", "RAYMOND", "CONCOR", "MGL", "DRREDDY", "BHEL", "SUZLON", "SOUTHBANK",
			"CENTURYTEX", "BPCL", "RBLBANK", "DCBBANK", "HDFC", "UBL", "INFY", "POWERGRID", "EXIDEIND",
			"LUPIN", "GAIL", "MARICO", "BEML", "PAGEIND", "COLPAL", "BERGEPAINT", "EQUITAS", "FEDERALBNK", "VOLTAS",
			"PCJEWELLER", "TORNTPHARM", "ASHOKLEY", "TATAELXSI", "TATASTEEL", "ULTRACEMCO", "IDFC", "BAJFINANCE",
			"HEROMOTOCO", "IOC", "HAVELLS", "WIPRO", "HDFCBANK", "WOCKPHARMA", "SUNTV", "EICHERMOT", "KOTAKBANK",
			"MRPL", "CADILAHC", "BHARTIARTL", "JUSTDIAL", "TITAN", "GODREJCP", "AUROPHARMA", "CANFINHOME", "HCLTECH",
			"BALKRISIND", "MOTHERSUMI", "TV18BRDCST", "MRF", "JPASSOCIAT", "ADANIPORTS", "HINDALCO", "CANBK", "SRF",
			"ICICIBANK", "SAIL", "HINDUNILVR", "ORIENTBANK", "KTKBANK", "UNIONBANK", "BIOCON", "GRASIM",
			"PIDILITIND", "SBIN", "ASIANPAINT", "MARUTI", "DLF", "ALBK", "HINDZINC", "NESTLEIND", "APOLLOHOSP",
			"RAMCOCEM", "PNB", "IFCI", "TATAGLOBAL", "BANKBARODA", "CGPOWER", "ARVIND", "SYNDIBANK", "HEXAWARE",
			"ESCORTS", "KAJARIACER", "HINDPETRO", "SIEMENS", "ACC", "SUNPHARMA", "ENGINERSIN", "INDIANB", "TVSMOTOR",
			"UJJIVAN", "ITC", "GSFC", "JSWSTEEL", "GODREJIND", "TORNTPOWER", "CESC", "COALINDIA", "SHREECEM",
			"ADANIENT", "YESBANK", "BAJAJFINSV", "RELIANCE", "BHARATFORG", "BANKINDIA", "INDIACEM", "RECLTD",
			"BOSCHLTD", "CEATLTD", "MCX", "JISLJALEQS", "NCC", "DIVISLAB", "CHOLAFIN", "KSCL", "TCS", "MINDTREE",
			"DABUR", "OFSS", "CUMMINSIND", "IDFCFIRSTB", "PVR", "IBULHSGFIN", "AMBUJACEM", "INDIGO", "ADANIPOWER",
			"TATAPOWER", "INFIBEAM", "IRB", "IDBI", "KPIT", "REPCOHOME" };
	public static final String[] NIFTY_FIFTY = { "RELIANCE", "JSWSTEEL", "TATAMOTORS", "BPCL", "WIPRO", "TCS",
			"ASIANPAINT", "TITAN", "IOC", "CIPLA", "TECHM", "HINDUNILVR", "BAJAJFINSV", "SUNPHARMA",
			"ULTRACEMCO", "GAIL", "HEROMOTOCO", "ONGC", "COALINDIA", "AXISBANK", "UPL", "MARUTI", "HDFCBANK", "HCLTECH",
			"BAJFINANCE", "ITC", "DRREDDY", "BRITANNIA",  "ICICIBANK", "POWERGRID", "HDFC", "BHARTIARTL", "INFY",
			"KOTAKBANK", "EICHERMOT", "LT", "ADANIPORTS", "NTPC", "SBIN", "TATASTEEL", "GRASIM", "INFRATEL", "ZEEL",
			"INDUSINDBK", "VEDL", "HINDALCO", "YESBANK", "IBULHSGFIN", };
}
