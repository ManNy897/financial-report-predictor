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
	
	public ReportData(String filename, String companySymbol, LocalDate date){
		this.filename = filename;
		stock = new Stock(companySymbol);
		this.date = date;
	}

	public String getFilename() {
		return filename;
	}

	public double getYTDChange() throws IOException{
		Calendar from = Calendar.getInstance();
		from.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
		Calendar to = Calendar.getInstance();
		to.set(date.getYear()+1, date.getMonthValue(), date.getDayOfMonth());
		System.out.println(stock.getSymbol() + " " + from.getTime().toString() + "  " + to.getTime().toString() );
		java.util.List<HistoricalQuote> data = stock.getHistory(from, to);
		int size = data.size();
		double initial = data.get(size-1).getClose().doubleValue();
		double fin = data.get(0).getClose().doubleValue();
		System.out.println("Initial Price: " + initial);
		System.out.println("Final Price: " + fin);
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
