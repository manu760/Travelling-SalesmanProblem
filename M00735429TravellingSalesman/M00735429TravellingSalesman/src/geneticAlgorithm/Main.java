package geneticAlgorithm;

import java.io.*;
import java.util.Scanner;


/**
 * Change the FILEPATH to run the algorithm on different data sets.
 * This file is to read the data from the file and to run genetic algorithm.
 *
 *
 */
public class Main{

	  // enter the path of the file to run
    private static final String FILEPATH = "C:\\Users\\ASUS\\eclipseProjects\\M00735429TravellingSalesman\\src\\files\\trainProblem1.txt";

    // file object to get the data from the file
    public static final File file = new File(FILEPATH);

    // display message if file does not found in the system
    public static final String FILE_NOT_FOUND = "File is not found by system";

    public static void main(String[] args) {
    	
    	//start time
		long time = System.nanoTime();
    	
        City[] dataPoints = getData();
        double minimumDistanceSoFar = Double.MAX_VALUE;
        int[] minimumDistancePathSoFar = new int[dataPoints.length];
        int maximumNumberOfGeneticAlgorithms = 20;
        //generate new genetic algorithms every iteration
        for(int geneticAlgorithmNumber = 0; geneticAlgorithmNumber < maximumNumberOfGeneticAlgorithms; geneticAlgorithmNumber++) {
            Genetic genetic = new Genetic(50, 15, dataPoints);

            int[][] population = genetic.initialGeneration();
            int maximumGenerations = 2000;

            for (int generationNumber = 0; generationNumber < maximumGenerations; generationNumber++) {
                int[] shortestGene = genetic.getShortestDistance(population);

                population = genetic.nextPopulation(shortestGene);

                double bestDistanceFromThisAttempt = genetic.minimumDistance;
                int[] bestDistancePathFromThisAttempt = genetic.minimumDistancePath;
                if(bestDistanceFromThisAttempt < minimumDistanceSoFar) {
                    minimumDistanceSoFar = bestDistanceFromThisAttempt;
                    minimumDistancePathSoFar = bestDistancePathFromThisAttempt;
                }
            }

        }

        System.out.print("SHORTEST PATH: ");
        Genetic.printGene(minimumDistancePathSoFar);
        System.out.println("\nSHORTEST DISTANCE: " + minimumDistanceSoFar);
        System.out.println("TIME: " + nanoTimeToSeconds(time) + " milliseconds\r\n");
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
     * This method will return the counter of number of lines in text file
     *
     * @param file To read the number of lines in text file
     * @return
     */
    private static int getTotalNumberOfDataPoints(File file) {
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
     * This file will return the number of Populations in the file
     *
     * @return the number of Populations in the form of x and y from the text file
     */
    protected static City[] getData() {

        City[] dataPoints = new City[getTotalNumberOfDataPoints(file)];

        int PopulationCounter = 0;

        String spacesFromFile = "[\t\r\n ]+";

        try {
            Scanner input = new Scanner(file).useDelimiter(spacesFromFile);
            while (input.hasNext()) {
                String[] line = input.nextLine().trim().split(spacesFromFile);
                int index = Integer.parseInt(line[0]);  //get the indexes of cities from the file 
                int xCoordinate = Integer.parseInt(line[1]); // get x co-ordinates
                int yCoordinate = Integer.parseInt(line[2]); //get y co-ordinates
                City dataPoint = new City(index, xCoordinate, yCoordinate);
                dataPoints[PopulationCounter] = dataPoint;
                PopulationCounter++;

            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(FILE_NOT_FOUND);
            fileNotFoundException.getMessage();
        }
        return dataPoints;
    }
	
	

}