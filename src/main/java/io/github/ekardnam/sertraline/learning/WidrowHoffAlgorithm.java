package io.github.ekardnam.sertraline.learning;

import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.data.DataProvider;

public class WidrowHoffAlgorithm extends LearningAlgorithm {

	@Override
	public boolean init(NeuralNetwork neuralNetwork) {
		return neuralNetwork.getLayers().size() == 2; //works on perceptrons
	}

	@Override
	public void algorithm(NeuralNetwork neuralNetwork, DataProvider dataProvider) {

	}
}
