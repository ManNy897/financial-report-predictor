
public class SnPByDate {
	private int[] date;
	private double price;
	
	public SnPByDate(int[] date, double price){
		this.date = date;
		this.price = price;
		
	}
	
	public int[] getDate(){
		return date;
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
	
	public double getPrice(){
		return price;
	}
	
	public void setDate(int[] date){
		this.date[0] = date[0];
		this.date[1] = date[1];
		this.date[2] = date[2];
	}
	
	
}
