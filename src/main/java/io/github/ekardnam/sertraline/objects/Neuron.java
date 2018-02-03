package io.github.ekardnam.sertraline.objects;

import java.util.ArrayList;

import io.github.ekardnam.sertraline.activation.ActivationFunction;

//a class that represents a neuron
public class Neuron {
	
	//in synapsis
	protected ArrayList<Synapsis> inLinks;
	
	//out synapsis
	protected ArrayList<Synapsis> outLinks;

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
		inLinks = new ArrayList<Synapsis>();
		outLinks = new ArrayList<Synapsis>();
		activationFunction = ActivationFunction.STEP_FUNCTION;
	}
	
	public Neuron(ActivationFunction activationFunction) {
		inLinks = new ArrayList<Synapsis>();
		outLinks = new ArrayList<Synapsis>();
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
	
	public ArrayList<Synapsis> getInLinks() {
		return inLinks;
	}
	
	public ArrayList<Synapsis> getOutLinks() {
		return outLinks;
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
