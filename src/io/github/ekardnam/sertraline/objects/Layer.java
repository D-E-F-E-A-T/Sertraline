package io.github.ekardnam.sertraline.objects;

import java.util.ArrayList;

public class Layer extends NetworkObject {
	
	private ArrayList<Neuron> neurons;
	
	public Layer() {
		neurons = new ArrayList<Neuron>();
	}
	
	public void newNeuron() {
		neurons.add(new Neuron());
	}
	
	public void addNeuron(Neuron n) {
		neurons.add(n);
	}
	
	public int getNeuronNumber() {
		return neurons.size();
	}
	
	public Neuron getNeuron(int i) {
		return neurons.get(i);
	}
	
	public ArrayList<Neuron> getNeurons() {
		return neurons;
	}
	
	public void runLayer() {
		for (Neuron n : neurons) {
			n.runNeuron();
		}
	}

}
