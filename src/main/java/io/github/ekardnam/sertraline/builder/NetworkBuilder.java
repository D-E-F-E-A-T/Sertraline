package io.github.ekardnam.sertraline.builder;

import com.sun.istack.internal.NotNull;
import io.github.ekardnam.sertraline.NeuralNetwork;

public interface NetworkBuilder {

	NetworkBuilder DEFAULT_BUILDER = new DefaultBuilder();
	NetworkBuilder ADALINE_BUILDER = new ADALINEBuilder();
	
	public void build(@NotNull NeuralNetwork network, @NotNull BuildPipeline pipeline);

}
