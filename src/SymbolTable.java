import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class meant to retrieve the stock ticker symbol for companys
 *
 */
public class SymbolTable {
	private HashMap<String, String> symbolTable;


	public SymbolTable(String symbolDir){
		symbolTable = buildTable(symbolDir);
	}

	/**
	 * Builds the mapping from company name to its symbol
	 * @param dir directory of the symbol mapping file
	 * @return the company name to symbol map
     */
	public HashMap<String, String> buildTable(String dir){
		HashMap<String, String> table = new HashMap<>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(dir));
			for(String line; (line = br.readLine()) != null;){
				String[] parts = line.split(",", 2);
				if(parts.length != 2) continue;
				String symbol = parts[0];
				String name = parts[1].toLowerCase().replaceAll("[^a-zA-Z0-9\\s&]", "").replace("corporation", "corp");
				table.put(name, symbol);
			}
			br.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		return table;
	}

	/**
	 * Retrieve the stock ticker symbol of the given company
	 * @param companyName
	 * @return the company's ticker symbol
     */
	public String lookupSymbol(String companyName){
		return symbolTable.get(companyName);
	}
	
}
