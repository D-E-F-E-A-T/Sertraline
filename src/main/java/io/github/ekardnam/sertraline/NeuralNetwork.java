package io.github.ekardnam.sertraline;

import java.util.List;

import io.github.ekardnam.sertraline.builder.LayerLinker;
import io.github.ekardnam.sertraline.builder.NetworkBuilder;
import io.github.ekardnam.sertraline.learning.LearningAlgorithm;
import io.github.ekardnam.sertraline.objects.Layer;

//a class that represents a neural network
public class NeuralNetwork {
	
	//the network builder
	protected NetworkBuilder builder;
	
	//the learning algorithm used
	protected LearningAlgorithm algo;
	
	//hidden layers of the network
	protected List<Layer> layers;

	protected LayerLinker linkToNextLinker;
	
	public void addLayer(Layer l) {
		if (linkToNextLinker != null) {
			linkToNextLinker.link(lastLayer(), l);
			linkToNextLinker = null;
		}
		layers.add(l);
	}

	public List<Layer> getLayers() { return layers; }

	public void linkToNextWith(LayerLinker linkToNextLinker) { this.linkToNextLinker = linkToNextLinker; }

	private Layer lastLayer() {
		return layers.get(layers.size() - 1);
	}

}
