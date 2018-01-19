package io.github.ekardnam.sertraline.learning;

import io.github.ekardnam.sertraline.NeuralNetwork;

public interface LearningAlgorithm {
	
	/**
	 * Prepares the algorithm
	 * @return true is succes
	 * */
	public boolean prepare(NeuralNetwork neuralNetwork);
	
	/**
	 * Trains the neural network
	 */
	public void train(NeuralNetwork neuralNetwork);

}
