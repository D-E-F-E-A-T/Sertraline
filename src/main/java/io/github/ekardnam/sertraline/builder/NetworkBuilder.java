package io.github.ekardnam.sertraline.builder;

import io.github.ekardnam.sertraline.NeuralNetwork;

public interface NetworkBuilder {

	NetworkBuilder DEFAULT_BUILDER = new DefaultBuilder();
	
	public void build(NeuralNetwork network, BuildPipeline pipeline);

}
