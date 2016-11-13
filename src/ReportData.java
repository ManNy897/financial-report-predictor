//import java.awt.List;
import java.io.IOException;
//import java.math.BigDecimal;
import java.util.Calendar;
import java.time.LocalDate;

import yahoofinance.*;
import yahoofinance.histquotes.HistoricalQuote;

public class ReportData {
	private String filename;
	private Stock stock;
	private LocalDate date;
	private int year;
	private int month;
	private int day;
	private SnP snp;
	
	public ReportData(String filename, String companySymbol, LocalDate date){
		this.filename = filename;
		stock = new Stock(companySymbol);
		this.date = date;
		int year = date.getYear();
		int month = date.getMonthValue()-1;
		int day = date.getDayOfMonth();
	}

	public String getFilename() {
		return filename;
	}

	public synchronized double getYTDChange() throws IOException{
		Calendar from = Calendar.getInstance();
		from.set(year, month, day);
		Calendar to = Calendar.getInstance();
		to.set(year+1, month, day);
		java.util.List<HistoricalQuote> data = stock.getHistory(from, to);
		int size = data.size();
		double initial = data.get(size-1).getClose().doubleValue();
		double fin = data.get(0).getClose().doubleValue();
		return (fin/initial);
		
		
	}
	
	public int getDay(){
		return date.getDayOfMonth();
	}
	
	public int getMonth(){
		return date.getMonthValue();
	}
	
	public int getYear(){
		return date.getYear();
	}
	
	public LocalDate getDate(){
		return date;
	}

	
}
