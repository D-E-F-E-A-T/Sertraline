package io.github.ekardnam.sertraline.learning;

import com.sun.istack.internal.NotNull;
import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.data.AbstractVector;
import io.github.ekardnam.sertraline.data.DataProvider;

/**
 * A class that represents a learning algorithm
 */
public abstract class LearningAlgorithm {

	/**
	 * Inits the algortihm
	 * @param network the {@link NeuralNetwork} to be trained
	 * @return true if init was successful else false
	 */
	protected abstract boolean init(@NotNull NeuralNetwork network);

	/**
	 * The implementation of the learning algorithm
	 * @param network the {@link NeuralNetwork} to be trained
	 * @param provider the {@link DataProvider} that provides data to train the network
	 */
	protected abstract void algorithm(@NotNull NeuralNetwork network, DataProvider provider);
	
	/**
	 * Trains the neural network
	 * @param network the {@link NeuralNetwork} to be trained
	 * @param provider the {@link DataProvider} that provides data to train the network
	 */
	public void train(@NotNull NeuralNetwork network, DataProvider provider) {
		if (!init(network)) return;
		algorithm(network, provider);
	}

	/**
	 * Helper function used to calculate the quadratic error between given output and expected output
	 * @param given the given output
	 * @param expected the expected output
	 * @return the quadratic between given and expected
	 */
	public static double quadraticError(@NotNull AbstractVector given, @NotNull AbstractVector expected) {
		return expected.subtract(given).square();
	}

}
