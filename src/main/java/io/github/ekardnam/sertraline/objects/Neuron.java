package io.github.ekardnam.sertraline.objects;

import java.util.ArrayList;
import java.util.List;

import io.github.ekardnam.sertraline.activation.ActivationFunction;

//a class that represents a neuron
public class Neuron {
	
	//in synapsis
	protected List<Synapsis> inLinks;
	
	//out synapsis
	protected List<Synapsis> outLinks;

	//activation function
	protected ActivationFunction activationFunction;

	public double out;
	
	public Neuron(ActivationFunction activationFunction) {
		inLinks = new ArrayList();
		outLinks = new ArrayList();
		this.activationFunction = activationFunction;
	}
	
	public Neuron(ActivationFunction activationFunction, double theta) {
		this(activationFunction);
		inLinks.add(new BiasSynapsis(this, theta));
	}
	
	public List<Synapsis> getInLinks() {
		return inLinks;
	}
	
	public List<Synapsis> getOutLinks() {
		return outLinks;
	}

	public Synapsis getLinkTo(Neuron n) {
		for (Synapsis s : outLinks) {
			if (s.to == n) {
				return s;
			}
		}
		return null;
	}

	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	//calculates the active potential
	protected double getPotential() {
		double potential = 0;
		for (Synapsis s : inLinks) {
			potential += s.getPotential();
		}
		return potential;
	}

	protected double getOutput() {
		return activationFunction.function(getPotential());
	}

	public void run() {
		out = getOutput();
	}

}
