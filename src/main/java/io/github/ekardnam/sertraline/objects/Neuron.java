package io.github.ekardnam.sertraline.objects;

import java.util.ArrayList;
import java.util.List;

import io.github.ekardnam.sertraline.activation.ActivationFunction;
import io.github.ekardnam.sertraline.data.AbstractVector;
import io.github.ekardnam.sertraline.data.Vector;
import javafx.util.Pair;

/**
 * A class that represents a {@link Neuron}
 * @author Luca Bertozzi - ekardnam lucabertozzi.pub@gmail.com
 */
public class Neuron extends IdentifiedObject {

	/**
	 * Synapsis coming into this neuron
	 */
	protected List<Synapsis> inLinks;

	/**
	 * Synapsis going out from this neuron
	 */
	protected List<Synapsis> outLinks;

	/**
	 * {@link ActivationFunction} used by this neuron
	 */
	protected ActivationFunction activationFunction;

	/**
	 * Output value of this neuron, helper attribute
	 */
	protected double out;

	/**
	 * Constructs a neuron with the given activation function
	 * @param activationFunction the {@link ActivationFunction}
	 */
	public Neuron(ActivationFunction activationFunction) {
		inLinks = new ArrayList<>();
		outLinks = new ArrayList<>();
		this.activationFunction = activationFunction;
	}

	/**
	 * Construct's a neuron with the given activation function and threshold value
	 * @param activationFunction the {@link ActivationFunction}
	 * @param theta the bias weight
	 */
	public Neuron(ActivationFunction activationFunction, double theta) {
		this(activationFunction);
		inLinks.add(new BiasSynapsis(this, theta));
	}

	/**
	 * Gets the synapsis coming into this neuron
	 * @return {@link java.util.List} of {@link Synapsis}
	 */
	public List<Synapsis> getInLinks() {
		return inLinks;
	}

	/**
	 * Gets the synapsis coming out of this neuron
	 * @return {@link java.util.List} of {@link Synapsis}
	 */
	public List<Synapsis> getOutLinks() {
		return outLinks;
	}

	/**
	 * Gets the synpsis that links to the given neuron
	 * @param n the neuron
	 * @return the {@link Synapsis} that links to n
	 */
	public Synapsis getLinkTo(Neuron n) {
		for (Synapsis s : outLinks) {
			if (s.to == n) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Gets the activation function used by this neuron
	 * @return the {@link ActivationFunction}
	 */
	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	/**
	 * Calculates the active potential of this neuron
	 * @return active potential
	 */
	protected double getPotential() {
		double potential = 0;
		for (Synapsis s : inLinks) {
			potential += s.getPotential();
		}
		return potential;
	}

	/**
	 * Returns the output value of this neuron
	 * To be set you have to call {@link #run()}
	 * @return the output {@link #out}
	 */
	public double getOutput() { return out; }

	/**
	 * Calculates and sets {@link #out}
	 */
	public void run() {
		out = activationFunction.function(getPotential());
	}

	/**
	 * Gets the weights' vector
	 * @return {@link AbstractVector} weights' vector
	 */
	public AbstractVector weights() {
		double weights[] = new double[inLinks.size()];
		for (int i = 0; i < inLinks.size(); i++) weights[i] = inLinks.get(i).w;
		return new Vector(inLinks.size(), weights);
	}

	/**
	 * Returns an {@link AbstractVector} containing the Neuron inputs
	 * @return the input vector
	 */
	public AbstractVector inputs() {
		double inputs[] = new double[inLinks.size()];
		for (int i = 0; i < inLinks.size(); i++) inputs[i] = inLinks.get(i).getInput();
		return new Vector(inLinks.size(), inputs);
	}

	/**
	 * Sets the weights' vector
	 * @param weights {@link AbstractVector} weights' vector
	 */
	public void setWeights(AbstractVector weights) {
		if (weights.getDimension() != inLinks.size()) throw new IllegalArgumentException("Must give as many weight as how many in links");
		for (int i = 0; i < inLinks.size(); i++) {
			inLinks.get(i).w = weights.get(i);
		}
	}

	/**
	 * Returns a Pair containing the inputs and outputs of this neuron
	 * @return the Pair
	 */
	public Pair<AbstractVector, AbstractVector> getWeightInputPair() {
		return new Pair<>(inputs(), weights());
	}

	/**
	 * Used to load a value in the {@link Neuron}
	 * @param out output value
	 */
	void loadValue(double out) { this.out = out; }

}
