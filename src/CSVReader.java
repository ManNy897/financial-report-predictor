import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
//	private String csvFile;
	private String[][] lookupTable;
	private int NUMBER_OF_COMPANIES = 27723;
	private String FILEPATH = "financial-report-predictor/data/data_info/symbols.csv"
	
	public CSVReader(){
		String line = "";
		String csvSplit = ",";
		int i = 0;
		lookupTable = new String[NUMBER_OF_COMPANIES][2];
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))){
			while((line = br.readLine()) != null){
				lookupTable[i] = line.split(csvSplit);
				if(lookupTable[i].length == 3){
					lookupTable[i][1] = lookupTable[i][1].substring(1) + "," + lookupTable[i][2].substring(0, lookupTable[i][2].length()-1);
				}
				i++;
			}		
			
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public String lookupSymbol(String companyName){
		int i = 0;
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
	}
	
}
