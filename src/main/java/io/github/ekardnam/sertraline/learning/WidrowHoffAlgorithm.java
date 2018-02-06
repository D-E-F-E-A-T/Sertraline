package io.github.ekardnam.sertraline.learning;

import com.sun.istack.internal.NotNull;
import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.data.*;
import io.github.ekardnam.sertraline.objects.Neuron;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class WidrowHoffAlgorithm extends LearningAlgorithm {

	public static final double DEFAULT_LEARNING_RATE = 0.005;
	public static final double DEFAULT_TARGET = 0.01;
	public static final int DEFAULT_MAX_EPOCHS = 1024;

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
	protected boolean algorithm(@NotNull NeuralNetwork network, DataProvider provider) {
		int howManyInputs = network.inputLayer().getHowManyNeurons();
		int howManyOutputs = network.outputLayer().getHowManyNeurons();

		if (howManyInputs != provider.howManyInputs() && howManyOutputs != provider.howManyOutputs()) {
			throw new IllegalArgumentException("Wrong data provided");
		}

		Function<Double, Double> derivative;
		if (network.getActivationFunction().derivable()) derivative = v -> network.getActivationFunction().derivative(v);
		else derivative = v -> (double) 1;
		for (int epoch = 0; epoch < maxEpochs; epoch++) {
			double epochError = 0;
			AbstractVector[] gradient = new AbstractVector[howManyOutputs];
			for (int i = 0; i < howManyOutputs; i++) gradient[i] = new Vector(howManyInputs + 1);
			for (DataUnit data : provider) {
				AbstractVector output = network.output(data.getInputs());
				AbstractVector error = data.getOutputs().subtract(output);
				for (int i = 0; i < howManyOutputs; i++) {
					Neuron n = network.outputLayer().get(i);
					gradient[i] = gradient[i].add(n.inputs().multiply(error.get(i) * derivative.apply(n.getOutput())));
				}
			}
			for (int i = 0; i < howManyOutputs; i++) {
				Neuron n = network.outputLayer().get(i);
				AbstractVector weights = n.weights();
				AbstractVector delta = gradient[i].multiply(-learningRate);
				n.setWeights(weights.add(delta));
			}
			if (epochError <= target) return true;
		}
		return false;
	}

}
