package io.github.ekardnam.sertraline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.github.ekardnam.sertraline.activation.ActivationFunction;
import io.github.ekardnam.sertraline.builder.FeedForwardLinker;
import io.github.ekardnam.sertraline.builder.LayerLinker;
import io.github.ekardnam.sertraline.builder.NetworkBuilder;
import io.github.ekardnam.sertraline.data.AbstractMatrix;
import io.github.ekardnam.sertraline.data.Matrix;
import io.github.ekardnam.sertraline.data.Vector;
import io.github.ekardnam.sertraline.data.VectorOperation;
import io.github.ekardnam.sertraline.learning.LearningAlgorithm;
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

	public static boolean isADALIN(NeuralNetwork network) {
		AtomicBoolean valid = new AtomicBoolean(true);
		for (int i = 0; i < 2; i++) {
			network.getLayers().get(i).forEach(neuron -> {
				if (neuron.getActivationFunction() != ActivationFunction.LINEAR_FUNCTION) {
					valid.set(false);
					return;
				}
			});
		}
		return valid.get() && isPerceptron(network);
	}

	@Override
	public Vector output(Vector input) {
		for (Layer l : layers) {
			l.run();
		}
		return outputLayer().getOutput();
	}

	public Matrix getWeightsMatrix(int index) {
		//TODO("Better illegal message")
		if (index == 0 || index > layers.size() - 1) throw new IllegalArgumentException("Illegal");

		Layer layer = layers.get(index);
		Layer before = layers.get(index - 1);
		if (!FeedForwardLinker.areLinkedFeedForwardly(before, layer)) return null;

		List<Vector> weights = new ArrayList();
		for (Neuron n : layer) {
			weights.add(n.weights());
		}

		return new Matrix(before.getHowManyNeurons(), layer.getHowManyNeurons(), (Vector[]) weights.toArray());
	}

	public static boolean isPerceptron(NeuralNetwork network) {
		return network.getLayers().size() == 2 && isFeedForward(network);
	}


	public static boolean isFeedForward(NeuralNetwork network) {
		for (int i = 1; i < network.getLayers().size(); i++) {
			Layer before = network.getLayers().get(i -1);
			Layer after = network.getLayers().get(i);
			if (!FeedForwardLinker.areLinkedFeedForwardly(before, after)) return false;
		}
		return true;
	}

}
