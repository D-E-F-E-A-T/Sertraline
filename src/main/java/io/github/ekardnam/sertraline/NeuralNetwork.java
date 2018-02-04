package io.github.ekardnam.sertraline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.github.ekardnam.sertraline.activation.ActivationFunction;
import io.github.ekardnam.sertraline.builder.FeedForwardLinker;
import io.github.ekardnam.sertraline.builder.LayerLinker;
import io.github.ekardnam.sertraline.data.*;
import io.github.ekardnam.sertraline.objects.Layer;
import io.github.ekardnam.sertraline.objects.Neuron;

//a class that represents a neural network
public class NeuralNetwork implements VectorOperation {
	
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

	public Layer outputLayer() { return lastLayer(); };

	@Override
	public AbstractVector output(AbstractVector input) {
		for (Layer l : layers) {
			l.run();
		}
		return outputLayer().getOutput();
	}

	public AbstractMatrix getWeightsMatrix(int index) {
		//TODO("Better illegal message")
		if (index == 0 || index > layers.size() - 1) throw new IllegalArgumentException("Illegal");

		Layer layer = layers.get(index);
		Layer before = layers.get(index - 1);
		if (!FeedForwardLinker.areLinkedFeedForwardly(before, layer)) return null;

		List<AbstractVector> weights = new ArrayList();
		for (Neuron n : layer) {
			weights.add(n.weights());
		}

		return new Matrix(before.getHowManyNeurons(), layer.getHowManyNeurons(), (AbstractVector[]) weights.toArray());
	}

	public ActivationFunction getActivationFunction() {
		AtomicReference<ActivationFunction> af = new AtomicReference(null);
		layers.forEach(layer -> {
			if (af.get() == null) af.set(layer.getActivationFunction());
			if (layer.getActivationFunction() != af.get()) {
				af.set(null);
				return;
			}
		});
		return af.get();
	}

	public static boolean isPerceptron(NeuralNetwork network) {
		return network.getLayers().size() == 2 && isFeedForward(network) && network.getActivationFunction() != null;
	}


	public static boolean isFeedForward(NeuralNetwork network) {
		for (int i = 1; i < network.getLayers().size(); i++) {
			Layer before = network.getLayers().get(i -1);
			Layer after = network.getLayers().get(i);
			if (!FeedForwardLinker.areLinkedFeedForwardly(before, after)) return false;
		}
		return network.getActivationFunction() != null;
	}

	public static boolean isADALINE(NeuralNetwork network) {
		return network.getActivationFunction() == ActivationFunction.LINEAR_FUNCTION && isPerceptron(network);
	}
}
