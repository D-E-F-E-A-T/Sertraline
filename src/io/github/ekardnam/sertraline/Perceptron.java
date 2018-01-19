package io.github.ekardnam.sertraline;

import io.github.ekardnam.sertraline.learning.LearningAlgorithm;
import io.github.ekardnam.sertraline.objects.Layer;

import java.util.ArrayList;


//a perceptron neural network
public class Perceptron extends NeuralNetwork {
	
	public Perceptron(int inputs) {
		super(inputs, 1);
	}
	
	public Perceptron(LearningAlgorithm learningAlgorithm, int inputs) {
		super(learningAlgorithm, inputs, 1);
	}
	
	@Override
	public ArrayList<Layer> getHiddenLayers() {
		throw new RuntimeException("Perceptron doesn't have an hidden layer");
	}

}
