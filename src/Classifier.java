import java.io.IOException;
import java.time.LocalDate;

public class Classifier {
	private SnP snp;
	
	public Classifier(){
		snp = new SnP();
	}

	public SnP getSnp() {
		return snp;
	}

	public boolean isOutperformer(ReportData rd) throws IOException{
		int[] thisYear = new int[3];
		int[] nextYear = new int[3];
		
		thisYear[0] = rd.getDay();
		thisYear[1] = rd.getMonth();
		thisYear[2] = rd.getYear();
		
		nextYear[0] = rd.getDay();
		nextYear[1] = rd.getMonth();
		nextYear[2] = rd.getYear() + 1; 

		LocalDate currYear = rd.getDate();
		LocalDate followingYear = currYear.plusYears(1);

		double compare = (snp.getSnPPrice(followingYear)/snp.getSnPPrice(rd.getDate()));
		System.out.println(compare);

		return (rd.getYTDChange() > compare);
	}
}
