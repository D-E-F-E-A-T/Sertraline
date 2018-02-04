package io.github.ekardnam.sertraline.learning;

import com.sun.istack.internal.NotNull;
import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.data.DataProvider;
import io.github.ekardnam.sertraline.data.DataUnit;
import io.github.ekardnam.sertraline.data.Vector;

class WidrowHoffAlgorithm extends LearningAlgorithm {

	public static final double DEFAULT_LEARNING_RATE = 0.005;
	public static final double DEFAULT_TARGET = 0.01;
	public static final int DEFAULT_MAX_EPOCHS = 100;

	protected double learningRate;

	protected double target;

	protected int maxEpochs;

	public WidrowHoffAlgorithm(double target, double learningRate, int maxEpochs) {
		this.target = target;
		this.learningRate = learningRate;
		this.maxEpochs = maxEpochs;
	}

	public WidrowHoffAlgorithm(double target, double learningRate) {
		this(target, learningRate, DEFAULT_MAX_EPOCHS);
	}

	public WidrowHoffAlgorithm(double target) {
		this(target, DEFAULT_LEARNING_RATE);
	}

	public WidrowHoffAlgorithm() {
		this(DEFAULT_TARGET);
	}

	@Override
	protected boolean init(@NotNull NeuralNetwork network) {
		return NeuralNetwork.isPerceptron(network); //works on perceptron
	}

	@Override
	protected void algorithm(@NotNull NeuralNetwork network, DataProvider provider) {
		for (int epoch = 0; epoch < maxEpochs; epoch++) {
			for (DataUnit data : provider) {

			}
		}
	}

	protected double getError(@NotNull NeuralNetwork network, Vector input, Vector expected) {
		return LearningAlgorithm.quadraticError(network.output(input), expected);
	}

}
