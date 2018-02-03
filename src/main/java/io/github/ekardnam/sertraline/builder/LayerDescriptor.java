package io.github.ekardnam.sertraline.builder;

import com.sun.istack.internal.NotNull;

public class LayerDescriptor {

	protected LayerBuilder builder;
	
	protected LayerLinker nextLayerLinker;
	
	protected int neurons;
	
	public LayerDescriptor(@NotNull LayerBuilder builder, LayerLinker nextLayerLinker, int neurons) {
		this.builder = builder;
		this.nextLayerLinker = nextLayerLinker;
		this.neurons = neurons;
	}
	
	public LayerBuilder getBuilder() {
		return builder;
	}
	
	public LayerLinker getNextLayerLinker() {
		return nextLayerLinker;
	}
	
	public int getHowManyNeurons() {
		return neurons;
	}

}
