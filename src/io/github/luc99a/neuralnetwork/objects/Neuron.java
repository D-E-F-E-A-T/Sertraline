package io.github.luc99a.neuralnetwork.objects;

import io.github.luc99a.neuralnetwork.transfer.TransferFunction;

import java.util.ArrayList;

//a class that represents a neuron
public class Neuron extends NetworkObject {
	
	//in synapsis
	private ArrayList<Synapsis> inLinks;
	
	//out synapsis
	private ArrayList<Synapsis> outLinks;
	
	//transfer function
	private TransferFunction transferFunction;
	
	//transfer function result
	public double transfer;
	
	//active potential
	public double potential;
	
	public double theta;
	
	public Neuron() {
		inLinks = new ArrayList<Synapsis>();
		outLinks = new ArrayList<Synapsis>();
		transferFunction = TransferFunction.STEP_FUNCTION;
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
	
	public int getInLinksNumber() {
		return inLinks.size();
	}
	
	public int getOutLinksNumbers() {
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
	
	public Synapsis getOutLinkTo(Neuron n) {
		for (Synapsis s : getOutLinks()) {
			if (s.getOut() == n) {
				return s;
			}
		}
		return null;
	}
	
	public Synapsis getInLinkTo(Neuron n) {
		for (Synapsis s : getInLinks()) {
			if (s.getIn() == n) {
				return s;
			}
		}
		return null;
	}
	
	public TransferFunction getTransferFunction() {
		return transferFunction;
	}
	
	//calculates the active potential
	private double activationFunction() {
		double potential = 0;
		for (Synapsis s : inLinks) {
			potential += s.w * s.getIn().transfer;
		}
		return potential;
	}
	
	//runs the neuron
	public void runNeuron() {
		potential = activationFunction();
		transfer = transferFunction.T(potential - theta);
	}

}
