package io.github.luc99a.neuralnetwork;

import io.github.luc99a.neuralnetwork.learning.LearningAlgorithm;
import io.github.luc99a.neuralnetwork.objects.Layer;

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
	
	@Override
	public Type getType() {
		return Type.PERCEPTRON;
	}

}
