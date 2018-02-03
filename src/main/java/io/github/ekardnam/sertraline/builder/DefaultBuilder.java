package io.github.ekardnam.sertraline.builder;

import io.github.ekardnam.sertraline.NeuralNetwork;

public class DefaultBuilder implements NetworkBuilder {

	@Override
	public void build(NeuralNetwork network, BuildPipeline pipeline) {
		for (LayerDescriptor ld : pipeline) {
			network.addLayer(ld.getBuilder().build(ld.getHowManyNeurons()));
			network.linkToNextWith(ld.getNextLayerLinker());
		}
	}

}
