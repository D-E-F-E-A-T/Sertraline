package io.github.ekardnam.sertraline.objects;

import io.github.ekardnam.sertraline.activation.ActivationFunction;
import io.github.ekardnam.sertraline.data.AbstractVector;
import io.github.ekardnam.sertraline.data.Vector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

	public AbstractVector getOutput() {
		List<Double> outputs = new ArrayList();
		for (Neuron n : neurons) {
			outputs.add(n.getOutput());
		}
		return new Vector(outputs.size(), (Double[]) outputs.toArray());
	}

	public ActivationFunction getActivationFunction() {
		AtomicReference<ActivationFunction> af = new AtomicReference(null);
		neurons.forEach(neuron -> {
			if (af.get() == null) af.set(neuron.getActivationFunction());
			if (neuron.getActivationFunction() != af.get()) {
				af.set(null);
				return;
			}
		});
		return af.get();
	}

}
