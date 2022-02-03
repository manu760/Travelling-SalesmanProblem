package geneticAlgorithm;

import java.io.*;
import java.util.Scanner;

/**
 * The genetic algorithm is implemented in this file.A generation is started by
 * creating a randomised index of the possibilities of each gene in a
 * generation.A new gene will be created based on the total number of data
 * points.The offspring is created by selecting the best genes.There is a
 * probability that each index of an offspring will be mutated after it produces
 * an offspring.The index starts from 0 for example is the file has 1,2,3,4 points 
 * this population will return the path 0,1,2,3,0 to form a cycle.
 * 
 *
 */
public class Genetic {

	// Number of total data points in file
	private final int numberOfDataPoints;

	// Getting array of Populations
	private final City[] dataPoints;

	// size of each population
	private final int populationSize;

	// probability that an index mutates
	private final int mutationProbability;

	// easier to compare initially
	double minimumDistance = Double.MAX_VALUE;

	// temporarily 1 size
	int[] minimumDistancePath = new int[1];
	

	/**
	 * 
	 * @param populationSize		Size of the population
	 * @param mutationProbability	probability to mutate
	 * @param dataPoints			number of data points
	 */
	public Genetic(int populationSize, int mutationProbability, City[] dataPoints) {
		this.populationSize = populationSize;
		this.mutationProbability = mutationProbability;
		this.dataPoints = dataPoints;
		numberOfDataPoints = dataPoints.length;
	}

	/**
	 * This function generates the next population of genes using fittestGene
	 * @param fittestGene	the best fittest gene to generate offsprings from
	 * @return				generation of genes from one fittest gene
	 */
	public int[][] nextPopulation(int[] fittestGene) {
		int[][] population = new int[populationSize][numberOfDataPoints];

		for (int geneIndex = 0; geneIndex < populationSize; geneIndex++) {
			int[] offspring = fittestGene.clone();

			// mutates
			for (int offspringIndex = 0; offspringIndex < numberOfDataPoints; offspringIndex++) {
				int percentage = (int) (Math.random() * 100);
				if (percentage <= mutationProbability) {
					int randomIndex = (int) (Math.random() * numberOfDataPoints);
					int tmp = offspring[offspringIndex];
					offspring[offspringIndex] = offspring[randomIndex];
					offspring[randomIndex] = tmp;
				}
			}

			population[geneIndex] = offspring;
		}

		return population;
	}

	/**
	 * this function returns the best gene which is shortest distance in this case
	 * @param population		a population of genes
	 * @return					shortestGene
	 */
	public int[] getShortestDistance(int[][] population) {
		double shortestDistance = Double.MAX_VALUE;
		int[] shortestGene = population[0];

		for (int[] dataPointIndexes : population) {
			double totaldistance = 0.0;
			for (int index = 1; index < numberOfDataPoints; index++) {
				int city1Index = dataPointIndexes[index];
				int city2Index = dataPointIndexes[index - 1];
				totaldistance += dataPoints[city1Index].getDistance(dataPoints[city2Index]);
			}
			totaldistance += dataPoints[dataPointIndexes[0]]
					.getDistance(dataPoints[dataPointIndexes[numberOfDataPoints - 1]]);

			if (totaldistance < shortestDistance) {
				shortestDistance = totaldistance;
				shortestGene = dataPointIndexes;
			}
		}

		if (shortestDistance < minimumDistance) {
			this.minimumDistance = shortestDistance;
			this.minimumDistancePath = shortestGene;
		}
		return shortestGene;
	}

	/**
	 * Generate a population of genes
	 * @return		created initial population of genes
	 */
	public int[][] initialGeneration() {
		int[][] population = new int[populationSize][dataPoints.length];
		for (int geneNumber = 0; geneNumber < populationSize; geneNumber++) {
			boolean[] indexesChosen = new boolean[numberOfDataPoints];
			for (int dataPointNumber = 0; dataPointNumber < dataPoints.length; dataPointNumber++) {
				int randomIndex = (int) (Math.random() * dataPoints.length);
				while (indexExists(indexesChosen, randomIndex))
					randomIndex = (int) (Math.random() * numberOfDataPoints);

				indexesChosen[randomIndex] = true;
				population[geneNumber][dataPointNumber] = randomIndex;
			}
		}
		return population;
	}

	/**
	 *  Determines whether an index exists already in a gene
	 * @param indexesChosen		index to choose 
	 * @param indexToAdd		index to add 
	 * @return					indexesChosen[indexToAdd]
	 */
	public boolean indexExists(boolean[] indexesChosen, int indexToAdd) {
		return indexesChosen[indexToAdd];
	}

	/**
	 * this method will print the next generations
	 * @param population		population to get the generations from
	 */
	public void printGeneration(int[][] population) {
		System.out.println("NEXT GENERATION");
		for (int y = 0; y < population.length; y++) {
			for (int x = 0; x < population[y].length; x++)
				System.out.print(population[y][x] + " ");
			System.out.println();
		}
	}

	/**
	 * this method will print the genes
	 * @param gene		array of genes
	 */
	public static void printGene(int[] gene) {
		for (int x = 0; x < gene.length; x++)
			System.out.print(gene[x] + " ");
		System.out.print(gene[0] + " ");
	}

}
