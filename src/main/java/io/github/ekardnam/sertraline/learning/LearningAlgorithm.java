package io.github.ekardnam.sertraline.learning;

import com.sun.istack.internal.NotNull;
import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.data.DataProvider;

public abstract class LearningAlgorithm {

	protected abstract boolean init(@NotNull NeuralNetwork network);

	protected abstract void algorithm(@NotNull NeuralNetwork network, DataProvider provider);
	
	/**
	 * Trains the neural network
	 */
	public void train(@NotNull NeuralNetwork network, DataProvider provider) {
		if (!init(network)) return;
		algorithm(network, provider);
	}

}
