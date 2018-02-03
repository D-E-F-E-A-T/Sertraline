package io.github.ekardnam.sertraline;

import java.util.ArrayList;
import java.util.List;

import io.github.ekardnam.sertraline.builder.LayerLinker;
import io.github.ekardnam.sertraline.builder.NetworkBuilder;
import io.github.ekardnam.sertraline.learning.LearningAlgorithm;
import io.github.ekardnam.sertraline.objects.Layer;

//a class that represents a neural network
public class NeuralNetwork {
	
	//hidden layers of the network
	protected List<Layer> layers;

	protected LayerLinker linkToNextLinker;

	public NeuralNetwork() {
		layers = new ArrayList();
	}
	
	public void addLayer(Layer l) {
		if (linkToNextLinker != null) {
			linkToNextLinker.link(lastLayer(), l);
			linkToNextLinker = null;
		}
		layers.add(l);
	}

	public void linkToNextWith(LayerLinker linkToNextLinker) { this.linkToNextLinker = linkToNextLinker; }

	private Layer lastLayer() {
		return layers.get(layers.size() - 1);
	}

	public List<Layer> getLayers() { return layers; }

}
