//import java.awt.List;
import java.io.IOException;
//import java.math.BigDecimal;
import java.util.Calendar;
import java.time.LocalDate;

import yahoofinance.*;
import yahoofinance.histquotes.HistoricalQuote;


/**
 * Class giving access to the date of a report and the year to date of
 * the company since releasing the report
 *
 */
public class ReportData {
	private Stock stock;
	private LocalDate date;
	
	public ReportData(String companySymbol, LocalDate date){
		stock = new Stock(companySymbol);
		this.date = date;
	}

	/**
	 * Gets the year to date change of the stock report and its date
	 * @return
	 * @throws IOException
     */
	public double getYTDChange() throws IOException{
		Calendar from = Calendar.getInstance();
		from.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());

		Calendar to = Calendar.getInstance();
		to.set(date.getYear()+1, date.getMonthValue(), date.getDayOfMonth());

		java.util.List<HistoricalQuote> data = stock.getHistory(from, to);
		int numYears = data.size();
		double initialClosing = data.get(numYears-1).getClose().doubleValue();
		double finalClosing = data.get(0).getClose().doubleValue();

		return (finalClosing/initialClosing);
	}

	public LocalDate getDate(){return date;}

	
}
