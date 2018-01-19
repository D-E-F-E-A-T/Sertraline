package io.github.ekardnam.sertraline.builder;

import io.github.ekardnam.sertraline.NeuralNetwork;

public interface NetworkBuilder {
	
	public void build(NeuralNetwork network, NetworkPattern pattern);

}
