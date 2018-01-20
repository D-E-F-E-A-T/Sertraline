package io.github.ekardnam.sertraline.objects;

import java.util.ArrayList;

import io.github.ekardnam.sertraline.transfer.TransferFunction;

//a class that represents a neuron
public class Neuron {
	
	//in synapsis
	protected ArrayList<Synapsis> inLinks;
	
	//out synapsis
	protected ArrayList<Synapsis> outLinks;
	
	//transfer function
	protected TransferFunction transferFunction;
	
	//transfer function result
	public double transfer;
	
	//active potential
	public double potential;
	
	//bias
	//TODO move to network structure
	public double theta;
	
	public Neuron() {
		inLinks = new ArrayList<Synapsis>();
		outLinks = new ArrayList<Synapsis>();
		transferFunction = TransferFunction.DEFAULT_FUNCTION;
	}
	
	public Neuron(TransferFunction transferFunction) {
		inLinks = new ArrayList<Synapsis>();
		outLinks = new ArrayList<Synapsis>();
		this.transferFunction = transferFunction;
	}
	
	public Neuron(TransferFunction transferFunction, double theta) {
		this(transferFunction);
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
	
	public TransferFunction getTransferFunction() {
		return transferFunction;
	}
	
	//calculates the active potential
	protected double activationFunction() {
		double potential = 0;
		for (Synapsis s : inLinks) {
			potential += s.w * s.getFrom().transfer;
		}
		return potential;
	}
	
	//runs the neuron
	public void runNeuron() {
		potential = activationFunction();
		transfer = transferFunction.function(potential - theta);
	}

}
