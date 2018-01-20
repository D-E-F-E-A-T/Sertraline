package io.github.ekardnam.sertraline;

import java.util.List;

import io.github.ekardnam.sertraline.builder.NetworkBuilder;
import io.github.ekardnam.sertraline.learning.LearningAlgorithm;
import io.github.ekardnam.sertraline.objects.Layer;

//a class that represents a neural network
public class NeuralNetwork {
	
	//the network builder
	protected NetworkBuilder builder;
	
	//the learning algorithm used
	protected LearningAlgorithm learningAlgorithm;
	
	//hidden layers of the network
	protected List<Layer> layers;
	
	public void addLayer(Layer l) {
		layers.add(l);
	}
	
}
