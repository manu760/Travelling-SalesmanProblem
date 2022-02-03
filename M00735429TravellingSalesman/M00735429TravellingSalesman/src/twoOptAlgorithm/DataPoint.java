package twoOptAlgorithm;

public class DataPoint {
	int index;
	int ID;
	public final double x;
	public  final double y;
	
	public DataPoint() {
		this.index = 0;
		this.x = 0;
		this.y = 0;
		this.ID = 0;
	}

	public DataPoint(int index, int ID, double x, double y) {
		this.index = index;
		this.ID = ID;
		this.x = x;
		this.y = y;
	}
	public int getIndex() {
		return index;
	}
	public int getID(){
		return this.ID;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getDistance(DataPoint point) {
		 return Math.sqrt( (Math.pow(this.x - point.getX(), 2)) + ( (Math.pow(this.y - point.getY(), 2)) ) );
	}
	@Override 
	public String toString() {

		return ("" + ID);
	}
}
