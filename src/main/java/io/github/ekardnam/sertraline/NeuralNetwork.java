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

/**
 * A class that represents a {@link NeuralNetwork}
 */
public class NeuralNetwork implements VectorOperation {

	/**
	 * Layers of the network
	 */
	protected List<Layer> layers;

	/**
	 * When calling {@link #addLayer(Layer)} the previous layer will be linked to it using this
	 */
	protected LayerLinker linkToNextLinker;

	/**
	 * Constructs a neural network
	 */
	public NeuralNetwork() {
		layers = new ArrayList();
	}

	/**
	 * Adds a {@link Layer} to the network
	 * @param l {@link Layer} to be added
	 */
	public void addLayer(Layer l) {
		if (linkToNextLinker != null) {
			linkToNextLinker.link(lastLayer(), l);
			linkToNextLinker = null;
		}
		layers.add(l);
	}

	/**
	 * When calling {@link #addLayer(Layer)} the previous layer will be linked to it using this
	 * @param linkToNextLinker {@link LayerLinker} linker to link with
	 */
	public void linkToNextWith(LayerLinker linkToNextLinker) { this.linkToNextLinker = linkToNextLinker; }

	/**
	 * Returns the last elemtent of {@link #layers}
	 * @return last element of {@link #layers}
	 */
	private Layer lastLayer() {
		return layers.get(layers.size() - 1);
	}

	/**
	 * Layers of the network
	 * @return return {@link #layers}
	 */
	public List<Layer> getLayers() { return layers; }

	/**
	 * Gets the output layer of the network
	 * @return output {@link Layer} of the network
	 */
	public Layer outputLayer() { return lastLayer(); };

	/**
	 * Gets the network input layer
	 * @return input {@link Layer} of the network
	 */
	public Layer inputLayer() { return layers.get(0); }

	/**
	 * Gets the output {@link AbstractVector} corresponding to the input
	 * @param input input vector type {@link AbstractVector}
	 * @return output vector
	 */
	@Override
	public AbstractVector output(AbstractVector input) {
		inputLayer().loadInputs(input);
		for (Layer l : layers) {
			l.run();
		}
		return outputLayer().getOutput();
	}

	/**
	 * Returns an {@link AbstractMatrix} with weights connetting layer of index index with layer of index - 1
	 * @param index index of the layer
	 * @return {@link AbstractMatrix} of weights
	 */
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

	/**
	 * If unique, returns the {@link ActivationFunction}
	 * @return the activation function if unique else null
	 */
	public ActivationFunction getActivationFunction() {
		AtomicReference<ActivationFunction> af = new AtomicReference(null);
		layers.forEach(layer -> {
			if (af.get() == null) af.set(layer.getActivationFunction());
			if (!layer.getActivationFunction().getClass().equals(af.get().getClass())) {
				af.set(null);
				return;
			}
		});
		return af.get();
	}

	/**
	 * Helper function to check if the given network is a perceptron
	 * @param network the network
	 * @return true if perceptron else false
	 */
	public static boolean isPerceptron(NeuralNetwork network) {
		return network.getLayers().size() == 2 && isFeedForward(network) && network.getActivationFunction() != null;
	}


	/**
	 * Helper function to check is the given network is a feed forward network
	 * @param network the network
	 * @return true if it feed forward else false
	 */
	public static boolean isFeedForward(NeuralNetwork network) {
		for (int i = 1; i < network.getLayers().size(); i++) {
			Layer before = network.getLayers().get(i -1);
			Layer after = network.getLayers().get(i);
			if (!FeedForwardLinker.areLinkedFeedForwardly(before, after)) return false;
		}
		return network.getActivationFunction() != null;
	}

	/**
	 * Helper function to check if a given network is an ADAptive LInear NEuron network
	 * @param network the network
	 * @return true if ADALINE else false
	 */
	public static boolean isADALINE(NeuralNetwork network) {
		return network.getActivationFunction() == ActivationFunction.LINEAR_FUNCTION && isPerceptron(network) && network.outputLayer().getHowManyNeurons() == 1;
	}
}
