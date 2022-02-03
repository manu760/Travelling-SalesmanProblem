package nearestAndBranchBound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	/**
	  Change the FILEPATH to run the algorithms on different data sets.
	 * 
	 * For travelling salesman problem, the nearest neighbour algorithm 
	 * works for a large number of cities within one minute.
	 * This algorithm gets optimal results most of the time but in some 
	 * cases the result is 3% less than optimal solution.
	 * 
	 * Results- Nearest neighbour Algorithm
	 * Train Problem1  	Path=[4, 2, 3, 1, 4] and Distance: 24.299999999999997
	 * Train Problem2 	Path=[1, 6, 4, 2, 3, 5, 7, 8, 1] and Distance: 66.93
	 * Train Problem3 	Path=[3, 4, 5, 7, 1, 2, 9, 6, 8, 3] and Distance: 229.51
	 * test1_2018 		Path=[9, 2, 10, 11, 1, 7, 6, 12, 3, 8, 5, 4, 9] and Distance: 287.97999999999996
	 * test2_2018 		Path=[6, 13, 1, 7, 11, 2, 4, 12, 5, 3, 9, 10, 8, 14, 6] and Distance: 661.49
	 * test3_2018 		Path=[8, 13, 9, 2, 11, 1, 10, 15, 3, 7, 16, 17, 6, 5, 4, 12, 14, 8] and Distance: 132766.16
	 * test4_2018 		Path=[23, 34, 27, 38, 1, 22, 11, 26, 16, 15, 2, 8, 39, 30, 37, 25, 14, 24, 31, 4, 18, 9, 13, 36, 35, 7, 33, 28, 3, 41, 5, 17, 21, 40, 10, 12, 42, 20, 19, 6, 32, 29, 23] and 
	 * 					Distance: 179918.19999999998
	 * test1_2019  		Path=[8, 6, 2, 3, 4, 10, 11, 9, 7, 1, 5, 8] and Distance: 11269.39
	 * test2_2019  		Path=[12, 11, 3, 2, 10, 1, 4, 5, 6, 7, 8, 9, 13, 12] and Distance: 208.5
	 * test3_2019  		Path=[1, 13, 15, 2, 7, 4, 6, 3, 8, 14, 9, 12, 10, 5, 11, 1] and Distance: 354.25
	 * test4_2019 		Path=[42, 10, 24, 45, 35, 26, 4, 2, 29, 5, 48, 39, 32, 21, 47, 11, 23, 14, 25, 13, 12, 15, 33, 46, 44, 18, 7, 28, 36, 30, 6, 37, 19, 27, 43, 17, 20, 40, 9, 1, 8, 38, 31, 22, 16, 3, 34, 41, 42]
	 * 					and Distance: 39236.840000000004
	 * test1_2020 		Path=[6, 4, 8, 5, 3, 1, 7, 12, 9, 2, 11, 10, 6] and Distance: 471.34999999999997
	 * test2_2020 		Path=[9, 14, 1, 8, 5, 6, 11, 10, 3, 13, 4, 2, 12, 7, 9] and Distance: 735.5
	 * test3_2020 		Path=[14, 3, 4, 10, 15, 12, 1, 17, 13, 7, 8, 9, 18, 16, 5, 2, 6, 11, 14] and Distance: 144117.55000000002
	 * test4_2020 		Path=[12, 18, 31, 3, 1, 25, 29, 14, 19, 15, 13, 21, 27, 24, 8, 16, 26, 10, 28, 20, 11, 32, 4, 22, 7, 9, 2, 17, 5, 30, 23, 6, 12] and Distance: 718636.7000000001

	 */
	
	/**
	 * 
	 * The travel salesman problem can be solved optimally 
	 * for up to fourteen cities using this algorithm. This
	 * algorithm gives optimal solution for problem having 
	 * 14 cities or less than 14 cities.
	 * 
	 * Results- Branch and Bound Algorithm
	 * Train Problem1	 Path=[4, 2, 3, 1, 4] and Distance: 24.299999999999997
	 * Train Problem2 	 Path=[8, 5, 6, 4, 2, 3, 1, 7, 8] and Distance: 65.64999999999999
	 * Train Problem3 	 Path=[9, 6, 8, 3, 4, 5, 7, 1, 2, 9] and Distance: 229.51
	 * test1_2018 		 Path=[12, 3, 5, 8, 4, 9, 2, 11, 10, 1, 7, 6, 12] and Distance: 273.86
	 * test2_2018		 Path=[6, 13, 1, 7, 11, 2, 4, 12, 5, 3, 9, 10, 8, 14, 6] Distance: 661.49
	 * test3_2018 		 NO PATH FOUND
	 * test4_2018		 NO PATH FOUND
	 * test1_2019		 Path=[11, 9, 7, 1, 6, 8, 5, 2, 3, 4, 10, 11] and Distance: 10605.100000000002
	 * test2_2019 		 NO PATH FOUND
	 * test3_2019 		 Path=[13, 12, 11, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13] and Distance: 196.0
	 * test4_2019		 NO PATH FOUND  
	 * test1_2020		 Path=[12, 7, 1, 3, 5, 8, 4, 6, 10, 11, 9, 2, 12] and Distance: 463.94999999999993
	 * test2_2020		 NO PATH FOUND
	 * test3_2020		 NO PATH FOUND
	 * test4_2020		 NO PATH FOUND
	 */

	// List of vertices
	private static List<Vertex> vertexList = new ArrayList<Vertex>();
	
	// List of paths
	private static List<Path> paths = new ArrayList<Path>();
	
	// Count vertices
	public static int vertexListCount = 0;
	
	// set shortest distance to infinity
	private static double shortestDistance = Double.MAX_VALUE;
	
	// get the shortest path
	private static Path shortestPath;

	// path for the file
	final static String FILENAME = "C:\\Users\\ASUS\\eclipseProjects\\M00735429TravellingSalesman\\src\\files\\trainProblem1.txt";
	
	//message for if the file is not found
	final static String FILE_NOT_FOUND = "The entered file is not found.";
	
	//message for input output exception
	final static String INPUT_OUTPUT_EXCEPTION = "Error in file.";

	/**
	 * this method is to get the data points from the text files
	 * 
	 * @return list of the vertices
	 */
	public static List getDataFromFile() {

		List<List> vertexList = new ArrayList<>(); // vertex list to input data
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

		File file = new File(FILENAME);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			Scanner scanner = new Scanner(reader);
			List<Integer> vertex = new ArrayList<>();
			// start read file
			while (scanner.hasNext()) {
				// if there is input int value
				if (scanner.hasNextInt()) {
					vertex.add(scanner.nextInt());// read one number
				} else {
					
					scanner.next();
				}
				// if is readied one line
				if (vertex.size() == 3) {
					vertexList.add(new ArrayList(vertex));
					vertex.clear();
				}
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println(FILE_NOT_FOUND);
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			System.out.println(INPUT_OUTPUT_EXCEPTION);
			ioException.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ioException) {
				System.out.println(INPUT_OUTPUT_EXCEPTION);
			}
		}
		// return the list with vertices

		return vertexList;
	}

	/**
	 * @return the distance between the paths
	 * @param vertex1 vertex to start with
	 * @param vertex2 vertex to end with
	 */
	static double getEuclideanDistance(List<Integer> vertex1, List<Integer> vertex2) {
		long index1 = Math.abs(vertex1.get(1) - vertex2.get(1));
		long index2 = Math.abs(vertex1.get(2) - vertex2.get(2));
		double distance = (double) Math.sqrt((double) (index1 * index1 + index2 * index2));
		return (double) Math.round(distance * 100) / 100; // fix to point under 2
	}

	/**
	 * this method will set the values to this.vertexList
	 * 
	 * @param inputList input list of the vertices
	 */
	private static void setVertexList(List<List> inputList) {
		for (int currentIndex = 0; currentIndex < inputList.size(); currentIndex++) { // i for current vertex
			List<Double> distanceList = new ArrayList<>();
			for (int nextIndex = 0; nextIndex < inputList.size(); nextIndex++) { // j for each other vertex
				distanceList.add(getEuclideanDistance(inputList.get(currentIndex), inputList.get(nextIndex)));// calculate euclidean distance
			}

			// add vertex with vertexId, visited, costList
			vertexList.add(new Vertex((Integer) inputList.get(currentIndex).get(0), false, distanceList));
		}
		vertexListCount = vertexList.size();
		vertexList.get(vertexListCount - 1).setVisited(true);
	}

	/**
	 * this method will return the total cost of the paths
	 * 
	 * @param path to calculate the distance
	 * @return
	 */
	private static Double getCourseDistance(Path path) {
		double totalCost = 0;
		// for vertex through path
		for (int index = 0; index < path.getCourseList().size() - 1; index++) {
			totalCost += path.getCourseList().get(index).getDistance(path.getCourseList().get(index + 1).getVertexIndex() - 1); // add the cost from vertex to next vertex
		}
		return totalCost;
	}

	/**
	 * this method is to calculate the path by recursion
	 * 
	 * @param path       path of the cities
	 * @param notVisited list of vertices that are not visited yet
	 */
	private static void recursion(Path path, List<Integer> notVisited) {
		if (!notVisited.isEmpty()) {

			for (int index = 0; index < notVisited.size(); index++) {
				// Pointer to first vertex in list
				int temp = notVisited.remove(0);

				Path newPath = new Path();
				for (Vertex vtx1 : path.getCourseList()) {
					newPath.getCourseList().add(vtx1);
				}
				// Add the first vertex from notVisited to the course
				newPath.getCourseList().add(vertexList.get(temp));

				if (paths.isEmpty()) {
					recursion(newPath, notVisited);
				} else if (getCourseDistance(newPath) < shortestDistance) {
					recursion(newPath, notVisited);
				}

				notVisited.add(temp);
			}
		} else { // Path is complete
			path.getCourseList().add(0, vertexList.get(vertexListCount - 1));
			path.getCourseList().add(vertexList.get(vertexListCount - 1));

			paths.add(path);
			// if is the shortest than saved distance
			if (getCourseDistance(path) < shortestDistance) {
				// replace to shortest
				shortestPath = path;
				shortestDistance = getCourseDistance(path);
			}
		}
	}

	/**
	 * this method calculates the shortest path using branch and bound algorithm
	 */
	private static void calculateBranchAndBound() {
		List<Integer> vertexNums = new ArrayList<Integer>();
		for (int count = 0; count < vertexListCount - 1; count++)
			vertexNums.add(count);
		System.out.println("branchAndBound:");
		//start time
		long time = System.nanoTime();
		// Calculate
		recursion(new Path(), vertexNums);

		// print out the best path and distance
		System.out.println("\t" + shortestPath.toString() + "\n\tDistance: " + getCourseDistance(shortestPath));
		System.out.println("\ttime: " + ((System.nanoTime() - time) / 1000000) + " milliseconds");

	}

	/**
	 * Calculate the shortest path using nearest neighbour algorithm
	 */
	private static void calculateNearestNeighbour() {
		System.out.println("nearestNeighbour:");
		//  start time
		long time = System.nanoTime();
		for (int startVtxIndex = 0; startVtxIndex < vertexListCount; startVtxIndex++) {
			// vertexList format
			for (Vertex vtx : vertexList) {
				vtx.setVisited(false);
			}
			vertexList.get(startVtxIndex).setVisited(true);
			double totalDistance = 0;
			// create new path with end vertex of input list
			Path nearestPath = new Path(vertexList.get(startVtxIndex));

			while (true) {
				// until created path not equal to list inputted
				if (nearestPath.getCourseList().size() == vertexListCount)
					break;

				Vertex neighbourVertex = null;
				double neighbourDistance = Double.MAX_VALUE;

				for (int i = 0; i < vertexListCount; i++) {
					// If closer and not self and not visited
					double tempDistance = nearestPath.getCurrentVertex().getDistance(i);
					// is short current distance than saved distance AND not self AND not visited
					if (tempDistance < neighbourDistance && tempDistance != 0
							&& vertexList.get(i).isVisited() == false) {
						// update
						neighbourVertex = vertexList.get(i);
						neighbourDistance = tempDistance;
					}
				}
				// if is shortest distance
				if (neighbourVertex != null) {
					// set current as visited
					neighbourVertex.setVisited(true);
					// add to path,
					nearestPath.getCourseList().add(neighbourVertex);
					// Update current location
					nearestPath.setCurrentVertex(neighbourVertex);
					// Add distance
					totalDistance += neighbourDistance;
				}
			}
			// Add distance from current vertex to started end vertex
			totalDistance += nearestPath.getStartVertex()
					.getDistance(nearestPath.getCurrentVertex().getVertexIndex() - 1);

			// Add stoke to route end
			nearestPath.getCourseList().add(vertexList.get(startVtxIndex));
			if (totalDistance < shortestDistance) {
				shortestDistance = totalDistance;
				shortestPath = nearestPath;
			}
		}
		System.out.println("\t" + shortestPath.toString() + "\n\tDistance: " + shortestDistance);
		System.out.println("\ttime: " + ((System.nanoTime() - time) / 1000000) + " milliseconds");
	}
	
	
	

	/**
	 * run both algorithms
	 * @param args
	 */
	public static void main(String[] args) {

		List<List> inputList = new ArrayList<>(getDataFromFile());
		// set to this.vertexList
		setVertexList(inputList);

		calculateNearestNeighbour();

		calculateBranchAndBound();

	}

}
