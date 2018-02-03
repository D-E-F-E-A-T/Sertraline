package io.github.ekardnam.sertraline.objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Layer implements Iterable<Neuron> {
	
	protected List<Neuron> neurons;
	
	public Layer() {
		neurons = new ArrayList();
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
