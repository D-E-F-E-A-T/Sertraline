package io.github.ekardnam.sertraline.objects;

import io.github.ekardnam.sertraline.activation.ActivationFunction;
import io.github.ekardnam.sertraline.data.AbstractVector;
import io.github.ekardnam.sertraline.data.Vector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A class that represents a layer of the neural network
 * @author Luca Bertozzi - ekardnam lucabertozzi.pub@gmail.com
 */
public class Layer implements Iterable<Neuron> {

	/**
	 * A List of the {@link Neuron}s of this layer
	 */
	protected List<Neuron> neurons;

	/**
	 * Constructs a new empty layer
	 */
	public Layer() {
		neurons = new ArrayList<>();
	}

	/**
	 * Add's the {@link Neuron} n to the layer
	 * @param n the neuron
	 */
	public void addNeuron(Neuron n) {
		neurons.add(n);
	}

	/**
	 * Returns how many neurons this layer contains
	 * @return the number of neurons contained by this layer
	 */
	public int howManyNeurons() {
		return neurons.size();
	}

	/**
	 * Gets the {@link Neuron} with index i
	 * @param i index of the neuron
	 * @return the {@link Neuron}
	 */
	public Neuron get(int i) { return neurons.get(i); }

	/**
	 * Implementation of Iterable to iterate over {@link Neuron}s
	 * @return an iterator to iterate over {@link Neuron}s
	 */
	@Override
	public Iterator<Neuron> iterator() {
		return neurons.iterator();
	}

	/**
	 * Runs the layer
	 */
	public void run() {
		for (Neuron n : neurons) {
			n.run();
		}
	}

	/**
	 * Returns the output of the layer
	 * @return an {@link AbstractVector} containing the output
	 */
	public AbstractVector getOutput() {
		List<Double> outputs = new ArrayList<>();
		for (Neuron n : neurons) {
			outputs.add(n.getOutput());
		}
		return new Vector(outputs.size(), outputs.stream().mapToDouble(value -> value).toArray());
	}

	/**
	 * Gets the {@link ActivationFunction} used by this layer if unique
	 * @return the {@link ActivationFunction} if unique else null
	 */
	public ActivationFunction getActivationFunction() {
		AtomicReference<ActivationFunction> af = new AtomicReference<>(null);
		neurons.forEach(neuron -> {
			if (af.get() == null) af.set(neuron.getActivationFunction());
			if (neuron.getActivationFunction() != af.get()) {
				af.set(null);
				return;
			}
		});
		return af.get();
	}

	/**
	 * Loads an {@link AbstractVector} into the layer inputs
	 * @param inputs {@link AbstractVector} containing the inputs
	 */
	public void loadInputs(AbstractVector inputs) {
		if (inputs.getDimension() != howManyNeurons()) throw new IllegalArgumentException("Illegal");

		for (int i = 0; i < howManyNeurons(); i++) {
			neurons.get(i).loadValue(inputs.get(i));
		}
	}

}
