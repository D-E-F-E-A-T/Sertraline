package io.github.luc99a.neuralnetwork.learning;

import io.github.luc99a.neuralnetwork.NeuralNetwork;

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
