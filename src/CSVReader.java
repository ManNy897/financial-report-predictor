import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CSVReader {
//	private String csvFile;
	private HashMap<String, String> lookupTable = new HashMap<String, String>();
	private int NUMBER_OF_COMPANIES = 27723;
	private String FILEPATH = "/Users/macpro/Documents/CS585/Capstone/Financial_Report_Predictor/data/snp_data/symbols.csv";
	
	public CSVReader(){
//		String line = "";
//		String csvSplit = ",";
//		int i = 0;
//		lookupTable = new String[NUMBER_OF_COMPANIES][2];
		try(BufferedReader br = new BufferedReader(new FileReader(FILEPATH))){
			for(String line; (line = br.readLine()) != null;){
				System.out.println(line);
				String[] parts = line.split(",", 2);
				if(parts.length != 2) continue;
				String symbol = parts[0];
				String name = parts[1].toLowerCase().replaceAll("[^a-zA-Z0-9\\s&]", "").replace("corporation", "corp");
				lookupTable.put(name, symbol);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}
	
	public String lookupSymbol(String companyName){
		return lookupTable.get(companyName);
/*		int i = 0;
		String currName = "";
		companyName = companyName.toLowerCase();
		while(i < NUMBER_OF_COMPANIES){
			if(lookupTable[i].length == 1){
				lookupTable[i] = new String[2];
				lookupTable[i][0] = "NO SYMBOL";
				lookupTable[i][1] = "NO NAME";
			}
			if(lookupTable[i][0] == null){
				lookupTable[i][0] = "NO SYMBOL";
				lookupTable[i][1] = "NO NAME";
			}
			if(lookupTable[i][1].charAt(0) == '"'){
				currName = lookupTable[i][1].toLowerCase();
				currName = currName.substring(1, currName.length());
			}
			else{
				currName = lookupTable[i][1].toLowerCase();
			}
			
			if(currName.equals(companyName)){
				return lookupTable[i][0];
			}
			
			else{
				i++;
			}
		}
		return "SYMBOL NOT FOUND";
		*/
	}
	
}
