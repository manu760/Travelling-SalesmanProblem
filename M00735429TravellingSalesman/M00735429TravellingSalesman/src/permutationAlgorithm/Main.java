package permutationAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	/**
	 *  Change the FILEPATH to run the algorithm on different data sets.
	 * 
	 * The travelling salesman problem using permutation algorithm 
	 * which extends an Interface called TravellingSalesmanProblem 
	 * to run the algorithm with run()
	 * method in Main.java file.
	 * 
	 * 
	 */

	// Number of total points in file
	private static int numberOfPoints;

	// Point class to get distance between point city1 and city2
	private static Point point = new Point();

	// Getting array of points
	private static  Point[] points;

	//enter the path of the file to run
			private static final String FILEPATH = "C:\\Users\\ASUS\\eclipseProjects\\M00735429TravellingSalesman\\src\\files\\trainProblem1.txt";

	// file object to get the data from the file
	public static File file = new File(FILEPATH);
	
	
	public static void main(String[] args) {
		points = getData();
		PermutationAlgorithm permutationAlgorithm = new PermutationAlgorithm(points);
		permutationAlgorithm.run();
		
	}
	

	/**
	 * This method will return the counter of number of lines in text file 
	 * @param file		To read the number of lines in text file 
	 * @return
	 */
	private static int getTotalNumberOfPoints(File file) {
		int counter = 0;
		try {

			Scanner input = new Scanner(file);
			while (input.hasNext()) {

				String line = input.nextLine();
				counter++;

			}

		} catch (FileNotFoundException ex) {
			ex.getMessage();
		}
		return counter;
	}

	/**
	 * This file will return the number of points in the file 
	 * @return		the number of points in the form of x and y from the text file 
	 */
	protected static Point[] getData() {

		numberOfPoints = getTotalNumberOfPoints(file);

		Point[] points = new Point[numberOfPoints];
		int pointCounter = 0;

		String spacesFromFile = "[\t\r\n ]+";

		try {
			Scanner input = new Scanner(file).useDelimiter(spacesFromFile);
			while (input.hasNext()) {
				String[] line = input.nextLine().trim().split(spacesFromFile);
				point = new Point(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
				points[pointCounter] = point;

				pointCounter++;

			}
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.getMessage();
		}
		return points;

	}



}
