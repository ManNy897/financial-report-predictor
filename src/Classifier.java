import java.io.IOException;

public class Classifier {
	private SnP snp;
	
	public Classifier(){
		snp = new SnP();
	}

	public SnP getSnp() {
		return snp;
	}

	public boolean isOutproformer(ReportData rd) throws IOException{
		int[] thisYear = new int[3];
		int[] nextYear = new int[3];
		
		thisYear[0] = rd.getDay();
		thisYear[1] = rd.getMonth();
		thisYear[2] = rd.getYear();
		
		nextYear[0] = rd.getDay();
		nextYear[1] = rd.getMonth();
		nextYear[2] = rd.getYear() + 1; 


		double compare = (snp.getSnPPrice(rd.getDate())/snp.getSnPPrice(rd.getDate()));

		return (rd.getYTDChange() > compare);
	}
}
