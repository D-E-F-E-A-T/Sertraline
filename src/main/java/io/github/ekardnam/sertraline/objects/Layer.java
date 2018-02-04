package io.github.ekardnam.sertraline.objects;

import io.github.ekardnam.sertraline.data.Vector;

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

	@Override
	public Iterator<Neuron> iterator() {
		return neurons.iterator();
	}

	public void run() {
		for (Neuron n : neurons) {
			n.run();
		}
	}



}
