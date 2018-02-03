package io.github.ekardnam.sertraline.objects;

import java.util.ArrayList;
import java.util.Iterator;

public class Layer implements Iterable<Neuron> {
	
	protected ArrayList<Neuron> neurons;
	
	public Layer() {
		neurons = new ArrayList<Neuron>();
	}
	
	public void newNeuron() {
		neurons.add(new Neuron());
	}
	
	public void addNeuron(Neuron n) {
		neurons.add(n);
	}
	
	public int getHowManyNeurons() {
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

	public Iterator<Neuron> iterator() {
		return new Iterator<Neuron>() {
			
			private int index = 0;

			public boolean hasNext() {
				return index < getHowManyNeurons();
			}

			@Override
			public Neuron next() {
				return getNeuron(index);
			}
			
			
		};
	}

}
