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
	private ActivationFunction activationFunction;
	
	//activation function result
	public double out;
	
	//active potential
	public double potential;
	
	public Neuron() {
		inLinks = new ArrayList();
		outLinks = new ArrayList();
		activationFunction = ActivationFunction.STEP_FUNCTION;
	}
	
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
	
	//calculates the active potential
	protected double potential() {
		double potential = 0;
		for (Synapsis s : inLinks) {
			potential += s.getPotential();
		}
		return potential;
	}
	
	//runs the neuron
	public void runNeuron() {
		potential = potential();
		out = activationFunction.function(potential);
	}

}
