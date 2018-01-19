package io.github.luc99a.neuralnetwork;

import io.github.luc99a.neuralnetwork.learning.PerceptronWidrowHoffAlgorithm;


public class Main {
	
	private static final int PERCEPTRON_INPUTS = 2;
	private static final double INPUTS[] = new double[] {1, 1, 1, 0, 0, 1, 0, 0};
	private static final double OUTPUTS[] = new double[] {1, 0, 0, 0};
	
	public static void main(String args[]) {
		Perceptron perceptron = new Perceptron(PERCEPTRON_INPUTS);
		perceptron.setLearningAlgorithm(new PerceptronWidrowHoffAlgorithm(INPUTS, OUTPUTS));
		perceptron.buildNetwork();
		perceptron.trainNetwork();
		System.out.println("1 AND 1 = " + perceptron.runNetwork(1, 1)[0]);
		System.out.println("1 AND 0 = " + perceptron.runNetwork(1, 0)[0]);
		System.out.println("0 AND 1 = " + perceptron.runNetwork(0, 1)[0]);
		System.out.println("0 AND 0 = " + perceptron.runNetwork(0, 0)[0]);
	}

}
