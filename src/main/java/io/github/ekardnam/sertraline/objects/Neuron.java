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
	
	//bias
	//TODO move to network structure
	public double theta;
	
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
		this.theta = theta;
	}
	
	public int getHowManyInLinks() {
		return inLinks.size();
	}
	
	public int getHowManyOutLinks() {
		return outLinks.size();
	}
	
	public void addInLink(Synapsis s) {
		inLinks.add(s);
	}
	
	public void addOutLink(Synapsis s) {
		outLinks.add(s);
	}
	
	public Synapsis getInLink(int i) {
		return inLinks.get(i);
	}
	
	public Synapsis getOutLink(int i) {
		return outLinks.get(i);
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
	protected double potential() {
		double potential = 0;
		for (Synapsis s : inLinks) {
			potential += s.w * s.getFrom().out;
		}
		return potential;
	}
	
	//runs the neuron
	public void runNeuron() {
		potential = potential();
		out = activationFunction.function(potential - theta);
	}

}
