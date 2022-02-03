package permutationAlgorithm;


public class Point {
	int index;

	public final double x;
	
	public  final double y;
	
	public Point() {
		this.index = 0;
		this.x = 0;
		this.y = 0;
		
		
	
	}

	public Point(int index,double x, double y) {
		this.index = index;
		this.x = x;
		this.y = y;
		
	
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}


	public double getDistance(Point point) {
		 return Math.sqrt( (Math.pow(this.x - point.getX(), 2)) + ( (Math.pow(this.y - point.getY(), 2)) ) );
	}

	

	@Override 
	public String toString() {

		return ("" + index );
		
	}
	

}
