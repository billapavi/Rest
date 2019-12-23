package com.rotbot.billa.Factory;

import java.util.HashMap;
import java.util.Map;

import com.rotbot.billa.stocks.*;

/**
 * Main aim of this class is to give class instance basing on the String given to you
 * @author billa
 *
 */
public class StockFactory {
	
	static Map<String, Class<SuperStock>> superStocks = new  HashMap<String, Class<SuperStock>>();
	static
	{
		
		// retreive all the classnames from the list 
	}
	
	

	public static void setStocksToFactory(String[] folist)
	{
		for (String name : folist) {
			
			System.out.println("case \""+name+"\":");
			System.out.println("return new "+name+"();");
		}
		System.out.println();
	}
	public static SuperStock getStock(String stockName)
	{
//		if(superStocks.get(stockName)==null)
//		{
//			System.out.println("stockName");
//		}
//		else
//		{
//			try {
//				return superStocks.get(stockName).newInstance();
//			} catch (InstantiationException | IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return null;
		switch (stockName) {
		case "RCOM":
			return new RCOM();
			case "RPOWER":
			return new RPOWER();
			case "RELINFRA":
			return new RELINFRA();
			case "DISHTV":
			return new DISHTV();
			case "SREINFRA":
			return new SREINFRA();
			case "DHFL":
			return new DHFL();
			case "INFRATEL":
			return new INFRATEL();
			case "JUBLFOOD":
			return new JUBLFOOD();
			case "BRITANNIA":
			return new BRITANNIA();
			case "IDEA":
			return new IDEA();
			case "RELCAPITAL":
			return new RELCAPITAL();
			case "NIITTECH":
			return new NIITTECH();
			case "GODFRYPHLP":
			return new GODFRYPHLP();
			case "ONGC":
			return new ONGC();
			case "IGL":
			return new IGL();
			case "NHPC":
			return new NHPC();
			case "ZEEL":
			return new ZEEL();
			case "STAR":
			return new STAR();
			case "MFSL":
			return new MFSL();
			case "GLENMARK":
			return new GLENMARK();
			case "JINDALSTEL":
			return new JINDALSTEL();
			case "MANAPPURAM":
			return new MANAPPURAM();
			case "OIL":
			return new OIL();
			case "APOLLOTYRE":
			return new APOLLOTYRE();
			case "TATACOMM":
			return new TATACOMM();
			case "TATAMOTORS":
			return new TATAMOTORS();
			case "AXISBANK":
			return new AXISBANK();
			case "CASTROLIND":
			return new CASTROLIND();
			case "LICHSGFIN":
			return new LICHSGFIN();
			case "PETRONET":
			return new PETRONET();
			case "TECHM":
			return new TECHM();
			case "VGUARD":
			return new VGUARD();
			case "NTPC":
			return new NTPC();
			case "BATAINDIA":
			return new BATAINDIA();
			case "TATACHEM":
			return new TATACHEM();
			case "NBCC":
			return new NBCC();
			case "PFC":
			return new PFC();
			case "BHARATFIN":
			return new BHARATFIN();
			case "UPL":
			return new UPL();
			case "VEDL":
			return new VEDL();
			case "NMDC":
			return new NMDC();
			case "PEL":
			return new PEL();
			case "TATAMTRDVR":
			return new TATAMTRDVR();
			case "GMRINFRA":
			return new GMRINFRA();
			case "NATIONALUM":
			return new NATIONALUM();
			case "AMARAJABAT":
			return new AMARAJABAT();
			case "MUTHOOTFIN":
			return new MUTHOOTFIN();
			case "INDUSINDBK":
			return new INDUSINDBK();
			case "LT":
			return new LT();
			case "ICICIPRULI":
			return new ICICIPRULI();
			case "SRTRANSFIN":
			return new SRTRANSFIN();
			case "JETAIRWAYS":
			return new JETAIRWAYS();
			case "CHENNPETRO":
			return new CHENNPETRO();
			case "AJANTPHARM":
			return new AJANTPHARM();
			case "CIPLA":
			return new CIPLA();
			case "BEL":
			return new BEL();
			case "RAYMOND":
			return new RAYMOND();
			case "CONCOR":
			return new CONCOR();
			case "MGL":
			return new MGL();
			case "DRREDDY":
			return new DRREDDY();
			case "BHEL":
			return new BHEL();
			case "SUZLON":
			return new SUZLON();
			case "SOUTHBANK":
			return new SOUTHBANK();
			case "CENTURYTEX":
			return new CENTURYTEX();
			case "BPCL":
			return new BPCL();
			case "RBLBANK":
			return new RBLBANK();
			case "DCBBANK":
			return new DCBBANK();
			case "HDFC":
			return new HDFC();
			case "UBL":
			return new UBL();
			case "INFY":
			return new INFY();
			case "POWERGRID":
			return new POWERGRID();
			case "EXIDEIND":
			return new EXIDEIND();
			case "LUPIN":
			return new LUPIN();
			case "GAIL":
			return new GAIL();
			case "MARICO":
			return new MARICO();
			case "BEML":
			return new BEML();
			case "PAGEIND":
			return new PAGEIND();
			case "COLPAL":
			return new COLPAL();
			case "BERGEPAINT":
			return new BERGEPAINT();
			case "EQUITAS":
			return new EQUITAS();
			case "FEDERALBNK":
			return new FEDERALBNK();
			case "VOLTAS":
			return new VOLTAS();
			case "PCJEWELLER":
			return new PCJEWELLER();
			case "TORNTPHARM":
			return new TORNTPHARM();
			case "ASHOKLEY":
			return new ASHOKLEY();
			case "TATAELXSI":
			return new TATAELXSI();
			case "TATASTEEL":
			return new TATASTEEL();
			case "ULTRACEMCO":
			return new ULTRACEMCO();
			case "IDFC":
			return new IDFC();
			case "BAJFINANCE":
			return new BAJFINANCE();
			case "HEROMOTOCO":
			return new HEROMOTOCO();
			case "IOC":
			return new IOC();
			case "HAVELLS":
			return new HAVELLS();
			case "WIPRO":
			return new WIPRO();
			case "HDFCBANK":
			return new HDFCBANK();
			case "WOCKPHARMA":
			return new WOCKPHARMA();
			case "SUNTV":
			return new SUNTV();
			case "EICHERMOT":
			return new EICHERMOT();
			case "KOTAKBANK":
			return new KOTAKBANK();
			case "MRPL":
			return new MRPL();
			case "CADILAHC":
			return new CADILAHC();
			case "BHARTIARTL":
			return new BHARTIARTL();
			case "JUSTDIAL":
			return new JUSTDIAL();
			case "TITAN":
			return new TITAN();
			case "GODREJCP":
			return new GODREJCP();
			case "AUROPHARMA":
			return new AUROPHARMA();
			case "CANFINHOME":
			return new CANFINHOME();
			case "HCLTECH":
			return new HCLTECH();
			case "BALKRISIND":
			return new BALKRISIND();
			case "MOTHERSUMI":
			return new MOTHERSUMI();
			case "TV18BRDCST":
			return new TV18BRDCST();
			case "MRF":
			return new MRF();
			case "JPASSOCIAT":
			return new JPASSOCIAT();
			case "ADANIPORTS":
			return new ADANIPORTS();
			case "HINDALCO":
			return new HINDALCO();
			case "CANBK":
			return new CANBK();
			case "SRF":
			return new SRF();
			case "ICICIBANK":
			return new ICICIBANK();
			case "SAIL":
			return new SAIL();
			case "HINDUNILVR":
			return new HINDUNILVR();
			case "ORIENTBANK":
			return new ORIENTBANK();
			case "KTKBANK":
			return new KTKBANK();
			case "UNIONBANK":
			return new UNIONBANK();
			case "BIOCON":
			return new BIOCON();
			case "GRASIM":
			return new GRASIM();
			case "PIDILITIND":
			return new PIDILITIND();
			case "SBIN":
			return new SBIN();
			case "ASIANPAINT":
			return new ASIANPAINT();
			case "MARUTI":
			return new MARUTI();
			case "DLF":
			return new DLF();
			case "ALBK":
			return new ALBK();
			case "HINDZINC":
			return new HINDZINC();
			case "NESTLEIND":
			return new NESTLEIND();
			case "APOLLOHOSP":
			return new APOLLOHOSP();
			case "RAMCOCEM":
			return new RAMCOCEM();
			case "PNB":
			return new PNB();
			case "IFCI":
			return new IFCI();
			case "TATAGLOBAL":
			return new TATAGLOBAL();
			case "BANKBARODA":
			return new BANKBARODA();
			case "CGPOWER":
			return new CGPOWER();
			case "ARVIND":
			return new ARVIND();
			case "SYNDIBANK":
			return new SYNDIBANK();
			case "HEXAWARE":
			return new HEXAWARE();
			case "ESCORTS":
			return new ESCORTS();
			case "KAJARIACER":
			return new KAJARIACER();
			case "HINDPETRO":
			return new HINDPETRO();
			case "SIEMENS":
			return new SIEMENS();
			case "ACC":
			return new ACC();
			case "SUNPHARMA":
			return new SUNPHARMA();
			case "ENGINERSIN":
			return new ENGINERSIN();
			case "INDIANB":
			return new INDIANB();
			case "TVSMOTOR":
			return new TVSMOTOR();
			case "UJJIVAN":
			return new UJJIVAN();
			case "ITC":
			return new ITC();
			case "GSFC":
			return new GSFC();
			case "JSWSTEEL":
			return new JSWSTEEL();
			case "GODREJIND":
			return new GODREJIND();
			case "TORNTPOWER":
			return new TORNTPOWER();
			case "CESC":
			return new CESC();
			case "COALINDIA":
			return new COALINDIA();
			case "SHREECEM":
			return new SHREECEM();
			case "ADANIENT":
			return new ADANIENT();
			case "YESBANK":
			return new YESBANK();
			case "BAJAJFINSV":
			return new BAJAJFINSV();
			case "RELIANCE":
			return new RELIANCE();
			case "BHARATFORG":
			return new BHARATFORG();
			case "BANKINDIA":
			return new BANKINDIA();
			case "INDIACEM":
			return new INDIACEM();
			case "RECLTD":
			return new RECLTD();
			case "BOSCHLTD":
			return new BOSCHLTD();
			case "CEATLTD":
			return new CEATLTD();
			case "MCX":
			return new MCX();
			case "JISLJALEQS":
			return new JISLJALEQS();
			case "NCC":
			return new NCC();
			case "DIVISLAB":
			return new DIVISLAB();
			case "CHOLAFIN":
			return new CHOLAFIN();
			case "KSCL":
			return new KSCL();
			case "TCS":
			return new TCS();
			case "MINDTREE":
			return new MINDTREE();
			case "DABUR":
			return new DABUR();
			case "OFSS":
			return new OFSS();
			case "CUMMINSIND":
			return new CUMMINSIND();
			case "IDFCFIRSTB":
			return new IDFCFIRSTB();
			case "PVR":
			return new PVR();
			case "IBULHSGFIN":
			return new IBULHSGFIN();
			case "AMBUJACEM":
			return new AMBUJACEM();
			case "INDIGO":
			return new INDIGO();
			case "ADANIPOWER":
			return new ADANIPOWER();
			case "TATAPOWER":
			return new TATAPOWER();
			case "INFIBEAM":
			return new INFIBEAM();
			case "IRB":
			return new IRB();
			case "IDBI":
			return new IDBI();
			case "KPIT":
			return new KPIT();
			case "REPCOHOME":
			return new REPCOHOME();

		default:
			return null;
		}
		
		
	}
}
