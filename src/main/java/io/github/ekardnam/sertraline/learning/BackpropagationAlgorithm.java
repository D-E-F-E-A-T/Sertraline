package io.github.ekardnam.sertraline.learning;

import com.sun.istack.internal.NotNull;
import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.data.*;
import io.github.ekardnam.sertraline.objects.Layer;
import io.github.ekardnam.sertraline.objects.Neuron;

import java.util.Collections;
import java.util.ListIterator;
import java.util.function.Function;


public class BackpropagationAlgorithm extends LearningAlgorithm {

	public static final double DEFAULT_LEARNING_RATE = 0.5;
	public static final double DEFAULT_TARGET = 0.01;
	public static final int DEFAULT_MAX_EPOCHS = 1024;

	protected double learningRate;

	protected double target;

	protected int maxEpochs;

	public BackpropagationAlgorithm(double target, double learningRate, int maxEpochs) {
		this.target = target;
		this.learningRate = learningRate;
		this.maxEpochs = maxEpochs;
	}

	public BackpropagationAlgorithm(double target, double learningRate) {
		this(target, learningRate, DEFAULT_MAX_EPOCHS);
	}

	public BackpropagationAlgorithm(double target) {
		this(target, DEFAULT_LEARNING_RATE);
	}

	public BackpropagationAlgorithm() {
		this(DEFAULT_TARGET);
	}

	@Override
	public boolean init(@NotNull NeuralNetwork network) {
		return NeuralNetwork.isFeedForward(network);
	}

	@Override
	public boolean algorithm(@NotNull NeuralNetwork network, DataProvider provider) {
		int howManyInputs = network.inputLayer().howManyNeurons();
		int howManyOutputs = network.outputLayer().howManyNeurons();

		if (howManyInputs != provider.howManyInputs() && howManyOutputs != provider.howManyOutputs()) {
			throw new IllegalArgumentException("Wrong data provided");
		}

		Function<Double, Double> derivative;
		if (network.getActivationFunction().derivable()) derivative = v -> network.getActivationFunction().derivative(v);
		else derivative = v -> (double) 1;

		NeuronVectorAllocator allocator = new NeuronVectorAllocator(network);

		for (int epoch = 0; epoch < maxEpochs; epoch++) {
			double epochError = 0;
			allocator.zeroMemory();
			for (DataUnit data : provider) {
				AbstractVector output = network.output(data.getInputs());
				AbstractVector error = data.getOutputs().subtract(output);
				epochError += LearningAlgorithm.quadraticError(output, data.getOutputs());

				//calculate the deltas for the output layer
				for (int i = 0; i < howManyOutputs; i++) {
					Neuron n = network.outputLayer().get(i);
					allocator.getVector(n).add(n.inputs().multiply(learningRate * error.get(i) * derivative.apply(n.getOutput())));
				}

			}

			//backpropagate the error in the hidden layers
			ListIterator<Layer> iterator = network.getLayers().listIterator(network.getLayers().size() - 1);
			while (iterator.previousIndex() != 0) {
				Layer l = iterator.previous();
				for (Neuron n : l) {

				}
			}

			if (epochError <= target) return true;
		}
		return false;
	}
}
