import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Class meant to classify each document as either over-performing or under-performing
 * based off the performance of the S&P
 *
 */
public class Classifier {
	private HashMap<LocalDate, Double> SnPTable;
	
	public Classifier(String snpTableDir){
		SnPTable = buildSNPTable(snpTableDir);
	}

	/**
	 * Build the mapping from date to snp closing price
	 * @param snpTableDir
	 * @return
     */
	private HashMap<LocalDate, Double> buildSNPTable(String snpTableDir){
		HashMap<LocalDate, Double> table = new HashMap<>();

		try{
			BufferedReader br = new BufferedReader(new FileReader(snpTableDir));
			for(String line; (line = br.readLine()) != null;){
				String[] parts = line.split(",", 2);
				String dateString = parts[0];
				Double price = new Double(parts[1].replace(",", ""));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
				LocalDate date = LocalDate.parse(dateString, formatter);
				table.put(date, price);
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return table;
	}

	/**
	 * Retrive the closing price of the snp on the given date
	 * @param date
	 * @return
     */
	private double getSnPPrice(LocalDate date){
		LocalDate currDate = date;
		while(!SnPTable.containsKey(currDate)){
			currDate = currDate.minusDays(1);
		}
		return SnPTable.get(currDate).doubleValue();
	}

	/**
	 * Determine whether the given report is underperformer or overperformer based off the snp
	 * @param rd
	 * @return
	 * @throws IOException
     */
	public boolean isOutperformer(ReportData rd) throws IOException{
		LocalDate currYear = rd.getDate();
		LocalDate followingYear = currYear.plusYears(1);

		double snpYTDChange = (getSnPPrice(followingYear)/getSnPPrice(rd.getDate()));
		double reportYTDChange = rd.getYTDChange();

		return (reportYTDChange > snpYTDChange);
	}
}
