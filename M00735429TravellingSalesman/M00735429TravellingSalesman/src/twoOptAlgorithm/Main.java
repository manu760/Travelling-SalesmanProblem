package twoOptAlgorithm;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	
	/**
	 *  Changing the constant String  FILENAME will make the algorithm run on another file.
	 * 
	 * 2-Opt algorithm for travelling salesman Problem
	 * 
	 * Results
	 * TrainProblem1 			Path: [1, 3, 2, 4, 1] Distance: 24.293023070189598
	 * TrainProblem2			Path: [8, 7, 1, 3, 2, 4, 6, 5, 8] Distance: 65.65395780324545
	 * TrainProblem3			Path: [2, 9, 6, 8, 3, 4, 5, 7, 1, 2] Distance: 229.50916652583456		
	 * test1_2018				Path: [2, 1, 7, 6, 12, 3, 5, 8, 4, 9, 11, 10, 2] Distance: 277.70985012417924
	 * test2_2018				Path: [10, 9, 3, 5, 12, 4, 2, 11, 7, 13, 6, 1, 14, 8, 10] Distance: 670.8351150957567
	 * test3_2018				Path: [7, 16, 17, 6, 5, 4, 14, 12, 8, 13, 11, 2, 9, 10, 1, 15, 3, 7] Distance: 126711.0040096348
	 * test4_2018				Path: [12, 10, 40, 2, 38, 8, 39, 30, 14, 37, 25, 21, 17, 5, 41, 3, 33, 28, 7, 35, 18, 4, 24, 31, 9, 13, 36, 23, 34, 27, 29, 1, 22, 11, 32, 6, 26, 16, 15, 19, 20, 42, 12] Distance: 184815.09456925254
	 * test1_2019				Path: [9, 7, 1, 6, 8, 5, 2, 3, 4, 10, 11, 9] Distance: 10605.096933318093
	 * test2_2019				Path: [13, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10, 11, 12, 13] Distance: 196.00581416951627
	 * test3_2019				Path: [14, 8, 7, 2, 15, 13, 1, 5, 10, 11, 12, 9, 6, 4, 3, 14] Distance: 336.6810973535037
	 * test4_2019				Path: [32, 39, 25, 14, 34, 3, 40, 15, 12, 11, 23, 13, 21, 47, 20, 33, 46, 36, 30, 43, 17, 27, 19, 37, 6, 28, 7, 18, 44, 31, 38, 9, 8, 1, 22, 16, 41, 2, 29, 5, 48, 42, 10, 26, 4, 35, 45, 24, 32] Distance: 34637.94299079316
	 * test1_2020				Path: [9, 2, 12, 7, 1, 3, 5, 8, 4, 6, 10, 11, 9] Distance: 463.9577484229663
	 * test2_2020				Path: [8, 7, 12, 2, 4, 13, 9, 14, 1, 10, 3, 11, 6, 5, 8] Distance: 781.8803750285281
	 * test3_2020				Path: [9, 8, 1, 12, 14, 16, 2, 5, 6, 11, 3, 15, 10, 4, 13, 17, 7, 18, 9] Distance: 145121.52604041822
	 * test4_2020				Path: [30, 5, 17, 2, 23, 6, 9, 4, 22, 7, 16, 8, 24, 27, 21, 19, 13, 15, 31, 12, 18, 3, 1, 25, 29, 14, 28, 10, 26, 20, 11, 32, 30] Distance: 605923.7135966554			 
	 */
	
	    // Number of total points in file
		private static int numberOfPoints;
		
		//Shortest distance of the path
		private static double shortestDistance;
	
		//enter the path of the file to run
		private static final String FILENAME = "C:\\Users\\ASUS\\eclipseProjects\\M00735429TravellingSalesman\\src\\files\\trainProblem1.txt";

		// Getting array of points
		private static List<DataPoint> points = new ArrayList<>();
		
		
		//display message if file does not found
		private static final String FILE_NOT_FOUND = " File does not found in the system.";

		public static void main(String[] args) {
					points = getData();
					numberOfPoints = points.size();

					//start time
					long time = System.nanoTime();
					twoOpt();
					points.add(numberOfPoints, points.get(0));

					System.out.println("\tPath: " + points);
					System.out.println("\tDistance: " + shortestDistance);
					System.out.println("\tTime: " + nanoTimeToSeconds(time) + " ms\r\n");
		
		}
	
		/**
		 * convert nano time to milliseconds 
		 * @param startTime
		 * @return
		 */
		public static long nanoTimeToSeconds(long startTime) {
			return  ((System.nanoTime() - startTime ) / 1000000);
			
		}
		/**
		 * This function will insert to points variable the data from txt file that has the number of points in the form of x and y
		 * @return	input List
		 */
		private static List<DataPoint> getData(){
			List<DataPoint> tempPoints = new ArrayList<>();

		    File file = new File(FILENAME);

			int index1 = 0;
			int index2 = 0;
			double xCoordinate = 0;
			double yCoordinate = 0;
			
			try {

			BufferedReader reader = new BufferedReader(new FileReader(file));
			Scanner scanner = new Scanner(reader);

			// start read file
			while (scanner.hasNext()) {
				//if there is input int value
				if (scanner.hasNextInt()) {
					index2 = scanner.nextInt();// read first number
				} else {
					scanner.next();
				}
				if (scanner.hasNextInt()) {
					xCoordinate = scanner.nextInt();// read second number
				} else {
					scanner.next();
				}
				if (scanner.hasNextInt()) {
					yCoordinate = scanner.nextInt();// read third number
				} else {
					scanner.next();
				}
				tempPoints.add(new DataPoint(index1, index2, xCoordinate, yCoordinate));
				index1++;
			}
			}catch(FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
				System.out.println(FILE_NOT_FOUND);	
			}

			return tempPoints;
		}
		
		/**
		 * do all 2-opt combinations
		 */
		private static  void twoOpt() {
			int improve = 1;
			// repeat until there is no improvement
			while (improve != 0){
				improve = 0;
				// length of the path
				shortestDistance = getDistance(points);
				for (int index1 = 0; index1 < numberOfPoints - 1; index1++) {
					for (int index2 = index1 + 1; index2 < numberOfPoints; index2++) {
						List<DataPoint> newPoints = twoOptSwap(index1, index2);
						double newDistance = getDistance(newPoints);
						if(newDistance < shortestDistance){
							points = newPoints;
							shortestDistance = newDistance;
							improve ++;
						}
					}
				}
			}
		}

	/**
	 * 
	 * @param index1		
	 * @param index2
	 * @return		two points that is swapped
	 */
		private static List<DataPoint> twoOptSwap(int index1,int index2) {
			List<DataPoint> newPoints = new ArrayList<>();

			// take points 0 to index1-1 and add them to new points
			for ( int index = 0; index <= index1 - 1; ++index )
			{
				newPoints.add(index, points.get(index));
			}

			// take points index1 to index2 and add them in reverse order to new points
			int dec = 0;
			for ( int index = index1; index <= index2; ++index)
			{
				newPoints.add( index, points.get( index2 - dec ) );
				dec++;
			}

			// take points index2+1 to end and add them to new points
			for ( int index = index2 + 1; index < numberOfPoints; ++index )
			{
				newPoints.add(index, points.get(index) );
			}
			return newPoints;
		}

		/**
		 * 
		 * @param pointList
		 * @return			return the total distance between the paths
		 */
		private static double getDistance(List <DataPoint> pointList) {
			double totaldistance = 0.0;
			for(int index = 1 ; index < pointList.size(); index++){
				totaldistance += pointList.get(index-1).getDistance(pointList.get(index));
			}
			totaldistance += pointList.get(0).getDistance(pointList.get(numberOfPoints - 1));
			return totaldistance;
		}

}
