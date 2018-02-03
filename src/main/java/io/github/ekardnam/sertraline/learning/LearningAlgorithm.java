package io.github.ekardnam.sertraline.learning;

import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.data.DataProvider;

public abstract class LearningAlgorithm {

	protected abstract boolean init(NeuralNetwork neuralNetwork);

	protected abstract void algorithm(NeuralNetwork neuralNetwork, DataProvider dataProvider);
	
	/**
	 * Trains the neural network
	 */
	public void train(NeuralNetwork neuralNetwork, DataProvider dataProvider) {
		if (!init(neuralNetwork)) return;
		algorithm(neuralNetwork, dataProvider);
	}

}
