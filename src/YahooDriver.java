import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by macpro on 11/13/16.
 */
public class YahooDriver {
    public static void main(String[] args) {
        Stock stock = new Stock("BPFHP");
        Calendar from = Calendar.getInstance();
        int year = 2005;
        int month = 3;
        int day = 15;
        from.set(year, month, day);
        Calendar to = Calendar.getInstance();
        to.set(year+1, month, day);
        try {
            System.out.println(stock.getSymbol() + "  " + from.getTime().toString() + "  " + to.getTime().toString());
            java.util.List<HistoricalQuote> data = stock.getHistory(from, to);
            int size = data.size();
            double initial = data.get(size-1).getClose().doubleValue();
            double fin = data.get(0).getClose().doubleValue();
            System.out.println("Initial Value: " + initial);
            System.out.println("Final Value: " + fin);
        }catch(IOException e){
            System.out.println("Error caught");
            e.printStackTrace();
        }

    }

}
