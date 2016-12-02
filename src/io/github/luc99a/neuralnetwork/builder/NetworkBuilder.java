package io.github.luc99a.neuralnetwork.builder;

import io.github.luc99a.neuralnetwork.NeuralNetwork;
import io.github.luc99a.neuralnetwork.objects.Layer;

public interface NetworkBuilder {
	
	/**
	 * Builds the neural network
	 * If not algorithm specific use defualt
	 * */
	public void build(NeuralNetwork neuralNetwork, int neuronsNumber[]);
	
	/**
	 * Links two layers
	 * If not algorithm specific use default
	 * */
	public void link(Layer l1, Layer l2);
	
	/**
	 * Builds the layer
	 * */
	public Layer layer(Layer l, int neuronNumber);
	
	/**
	 * Provides random synapsis weight
	 * If not algorithm specific use default
	 * */
	public double random();

}
