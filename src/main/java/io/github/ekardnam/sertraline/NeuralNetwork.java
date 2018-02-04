package io.github.ekardnam.sertraline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.github.ekardnam.sertraline.activation.ActivationFunction;
import io.github.ekardnam.sertraline.builder.LayerLinker;
import io.github.ekardnam.sertraline.builder.NetworkBuilder;
import io.github.ekardnam.sertraline.data.AbstractMatrix;
import io.github.ekardnam.sertraline.data.Vector;
import io.github.ekardnam.sertraline.data.VectorOperation;
import io.github.ekardnam.sertraline.learning.LearningAlgorithm;
import io.github.ekardnam.sertraline.objects.Layer;

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

	public static boolean isADALIN(NeuralNetwork network) {
		if (!isPerceptron(network)) return false;
		AtomicBoolean valid = new AtomicBoolean(true);
		for (int i = 0; i < 2; i++) {
			network.getLayers().get(i).forEach(neuron -> {
				if (neuron.getActivationFunction() != ActivationFunction.LINEAR_FUNCTION) {
					valid.set(false);
					return;
				}
			});
		}
		return valid.get();
	}

	@Override
	public Vector times(Vector vector) {
		return null;
	}

	public Vector output(Vector input) {
		return times(input);
	}

	public static boolean isPerceptron(NeuralNetwork network) {
		return network.getLayers().size() == 2;
	}
}
