package io.github.ekardnam.sertraline.builder;

import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.objects.Layer;

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
