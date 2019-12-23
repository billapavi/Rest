package com.rotbot.billa;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.rotbot.billa.Factory.StockFactory;
import com.rotbot.billa.beans.Candle;
import com.rotbot.billa.beans.MyPojo;
import com.rotbot.billa.repositories.StockRepository;
import com.rotbot.billa.stocks.SuperStock;
import com.rotbot.billa.utils.TestUtilites;
@Component
public class DataSeeder implements CommandLineRunner{

	public static Map<String, String> stockNameValues = new HashMap<>();
	public static Map<String, String> stockValuesNames = new HashMap<>();
	@Autowired
	public StockRepository stockRepo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	loadStockNamesAndValues();
		
//	runAndSaveStocks();
	testing();
	
	}

	private void testing() {
		// TODO Auto-generated method stub
		RestTemplate template = new RestTemplate();
		
//		createRepofiles();
	}

	private void createRepofiles() {
		Arrays.stream(TestUtilites.foList).forEach(stockName->{
			
			Path path = Paths.get("F:\\SpringBoot\\workspace\\Rest\\src\\main\\java\\com\\rotbot\\billa\\repositories\\"+stockName+"Repository.java");
			 
			//Use try-with-resource to get auto-closeable writer instance
			try (BufferedWriter writer = Files.newBufferedWriter(path))
			{
			    writer.write("package com.rotbot.billa.repositories;\n");
			    writer.write("import org.springframework.data.jpa.repository.JpaRepository;\n");
			    writer.write("import org.springframework.data.repository.CrudRepository;\n");
			    writer.write("import org.springframework.stereotype.Component;\n");
			    writer.write("import org.springframework.stereotype.Repository;\n");
			    writer.write("import com.rotbot.billa.stocks."+stockName.toUpperCase()+";\n");
			    writer.write("import com.rotbot.billa.stocks.SuperStock;\r\n" + 
			    		"			    @Component\r\n" + 
			    		"			    @Repository\r\n" + 
			    		"			    public interface "+stockName+"Repository extends JpaRepository<"+stockName.toUpperCase()+",String>{\r\n" + 
			    		"			     \r\n" + 
			    		"			    }");
			    

			    
			    
			    

			    
			    
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

	private void runAndSaveStocks() {
		String stname = "LUPIN";
			Map<String, List<Candle>> ss = TestUtilites.createMinDataOneDay(stockNameValues.get(stname), "2018-01-01", "2019-01-31", "minute", "");
			List<SuperStock> sList = new ArrayList<>();
			 
			ss.get(stockNameValues.get(stname))
			.forEach(candle->{
				if(candle!=null)
				{
					
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
				}
				else
				{
					System.out.println("candle is null billa");
				}
				
						
			});
			stockRepo.saveAll(sList);
			System.out.println("its done");
	}

	private void loadStockNamesAndValues() {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("AllStocks.properties"));
//			prop.load(getClass().getClassLoader().getResourceAsStream("FO.properties"));
			prop.forEach((k,v)->{
				if(k.toString().contains("-")||k.toString().contains("&"))
				{
					//skip
				}
				else
				{
					
//					System.out.println(k+" = "+v);
				}
				stockNameValues.put(k.toString(), v.toString());
				stockValuesNames.put(v.toString(), k.toString());
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StockFactory.setStocksToFactory(TestUtilites.foList);
	}

}
