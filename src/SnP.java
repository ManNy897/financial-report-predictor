import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.time.LocalDate;

public class SnP {
//	private SnPByDate[] closingPrice;
	private HashMap<LocalDate, Double> SnPTable = new HashMap<>();
	public static final int NUM_DATA_POINTS = 3023;
	public static final String SNPFILENAME = "/Users/macpro/Documents/CS585/Capstone/Financial_Report_Predictor/data/snp_data/SnP_closing_prices.csv";
	
	public SnP(){
//		closingPrice = new SnPByDate[3023];
		String line = "";
		String csvSplit = ",|-";
		
		int i = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(SNPFILENAME))){
			while((line = br.readLine()) != null){
				String[] str = line.split(csvSplit);
				String month = str[1];
				String monthNum;

				String[] parts = line.split(",", 2);
				String dateString = parts[0];
				Double price = new Double(parts[1].replace(",", ""));
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
				LocalDate date = LocalDate.parse(dateString, formatter);
				SnPTable.put(date, price);
				
//				switch (month){
//				case "Jan": monthNum = "1";
//					break;
//				case "Feb": monthNum = "2";
//					break;
//				case "Mar": monthNum = "3";
//					break;
//				case "Apr": monthNum = "4";
//					break;
//				case "May": monthNum = "5";
//					break;
//				case "Jun": monthNum = "6";
//					break;
//				case "Jul": monthNum = "7";
//					break;
//				case "Aug": monthNum = "8";
//					break;
//				case "Sep": monthNum = "9";
//					break;
//				case "Oct": monthNum = "10";
//					break;
//				case "Nov": monthNum = "11";
//					break;
//				case "Dec": monthNum = "12";
//					break;
//				default: monthNum = "13";
//					break;
//				}
//
//				str[1] = monthNum;
//				int[] date = {Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2])};
//				double price = Double.parseDouble(str[3]);
//				SnPByDate close = new SnPByDate(date, price);
//				closingPrice[i] = close;
//				i++;
			}		
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	//assumes date is after January 2, 1996
	public double getSnPPrice(LocalDate date){
		return SnPTable.get(date).doubleValue();
//		int i = 0;
//		while(i < NUM_DATA_POINTS){
//			if(closingPrice[i].getYear() == date[2] && closingPrice[i].getMonth() == date[1]){
//				if(closingPrice[i].getDay() == date[0]){
//					return closingPrice[i].getPrice();
//				}
//				else if(closingPrice[i].getDay() > date[0]){
//					return closingPrice[i-1].getPrice();
//				}
//			}
//			i++;
//		}
//		return 0;
	}
}
