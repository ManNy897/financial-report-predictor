import java.awt.List;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import yahoofinance.*;
import yahoofinance.histquotes.HistoricalQuote;

public class ReportData {
	private Stock stock;
	private int[] date;
	private Calendar to;
	private Calendar from;
	private SnP snp;
	
	public ReportData(String companySymbol, int[] date, SnP snp){
		stock = new Stock(companySymbol);
		this.date = date;
		this.snp = snp;
		int year = date[2];
		int month = date[1]-1;
		int day = date[0];
		from = Calendar.getInstance();
		from.set(year, month, day);
		to = Calendar.getInstance();
		to.set(year+1, month, day);
		
		
	}
	
	
	public double getYTDChange() throws IOException{
		java.util.List<HistoricalQuote> data = stock.getHistory(from, to);
		int size = data.size();
		double initial = data.get(size-1).getClose().doubleValue();
		double finl = data.get(0).getClose().doubleValue();
		return (finl/initial);
		
		
	}
	
	public int getDay(){
		return date[0];
	}
	
	public int getMonth(){
		return date[1];
	}
	
	public int getYear(){
		return date[2];
	}
	
	public int[] getDate(){
		return date;
	}

	
}
