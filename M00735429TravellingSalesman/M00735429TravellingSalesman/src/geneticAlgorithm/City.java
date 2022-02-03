package geneticAlgorithm;

public class City {
	int index;

	public final double city1;

	public final double city2;

	public City(int index) {
		this.index = index;
		this.city1 = 0;
		this.city2 = 0;

	
	}
	public City() {
		this.index = 0;
		this.city1 = 0;
		this.city2 = 0;
	}
	
	

	public City(int index,double city1, double city2) {
		this.index = index;
		this.city1 = city1;
		this.city2 = city2;
		
	
	}
	public City(double city1, double city2) {
		this.city1 = city1;
		this.city2 = city2;
		
	
	}

	public double getCity1() {
		return city1;
	}

	public double getCity2() {
		return city2;
	}

	public double getDistance(City point) {
		return Math.sqrt((Math.pow(this.city1- point.getCity1(), 2)) + ((Math.pow(this.city2 - point.getCity2(), 2))));
	}

	@Override
	public String toString() {

		return ("" + index);

	}

}
