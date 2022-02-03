package permutationAlgorithm;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PermutationAlgorithm implements TravellingSalesmanProblem {
	
	/** 
	 * The permutation method finds the best path and the minimum distance between paths in the case of a maximum of 10 cities. 
	 * This method only applies to the first three train problems.
	 * 
	 * How to run this algorithm- 
	 * In order to see the results of this algorithm, uncomment permutationAlgorithm.run() in application.Main.
	 */

	/**
	 * This algorithm is working for first three train problems 
	 * trainProblem1 Best Path - [1, 3, 2, 4, 1] and Distance - 24.293023070189598 
	 * trainProblem2 Best  Path - [1, 7, 8, 5, 6, 4, 2, 3, 1] and Distance - 65.65395780324545
	 * trainProblem3 Best Path - [1, 2, 9, 6, 8, 3, 4, 5, 7, 1] and Distance - 229.50916652583456
	 */


	// Current Minimum distance set to infinity
	private double currentMinimumDistance = Double.MAX_VALUE;

	private Point[] currentMinimumPath = null;

	// Point class to get the distance between point city1 and city2
	private Point point = new Point();

	// Getting array of points
	private Point[] points;

	public PermutationAlgorithm(Point[] points) {
this.points = points;
	}

	/**
	 * This method returns the possible permutations of points
	 * 
	 * @param point      get the points from the file and find the distance
	 * @param startIndex index of the point to permute
	 */

	public void getPossiblePermutations(Point[] point, int startIndex) {

		int count = 0;
		for (int index = startIndex; index < point.length; index++) {
			Point temp = point[startIndex];
			point[startIndex] = point[index];
			point[index] = temp;
			getPossiblePermutations(point, startIndex + 1);
			point[index] = point[startIndex];
			point[startIndex] = temp;
		}
		if (startIndex == point.length - 1) {

			point = getCycle(point);
			double distance = calculatDistanceOfPath(point);
			if (distance < currentMinimumDistance) {
				currentMinimumDistance = distance;
				currentMinimumPath = point;
			}
			count++;
		}
	}

	/**
	 * This method returns the current path of points into a cycle.
	 * 
	 * @param points array to add the startIndex at the end of the path
	 * @return cycle of the points
	 */
	public Point[] getCycle(Point[] points) {

		Point[] points1 = new Point[points.length + 1];
		System.arraycopy(points, 0, points1, 0, points.length);
		points1[points1.length - 1] = points1[0];
		System.out.println(Arrays.toString(points1));

		return points1;

	}

	/**
	 * This method returns the total distance between the permuted paths
	 * 
	 * @param points calculate the distance of points
	 * @return
	 */
	public double calculatDistanceOfPath(Point[] points) {

		double totalDistance = 0.0;

		for (int index = 1; index < points.length; index++) {
			Point point1 = points[index - 1];
			Point point2 = points[index];
			totalDistance += point1.getDistance(point2);
		}

		return totalDistance;

	}

	@Override
	public void run() {

		
		getPossiblePermutations(points, 0);
		System.out.println(
				"\nBest Path is " + Arrays.toString(currentMinimumPath) + " Distance - " + currentMinimumDistance);
	}

}
