package io.github.luc99a.neuralnetwork.builder;

import io.github.luc99a.neuralnetwork.NeuralNetwork;
import io.github.luc99a.neuralnetwork.objects.Layer;
import io.github.luc99a.neuralnetwork.objects.Neuron;
import io.github.luc99a.neuralnetwork.objects.Synapsis;
import io.github.luc99a.neuralnetwork.transfer.TransferFunction;

import java.util.ArrayList;
import java.util.Random;

public class DefaultNetworkBuilder implements NetworkBuilder {
	
	private Random rand = new Random();

	public void build(NeuralNetwork neuralNetwork, int neuronsNumber[]) {
		int i;
		boolean hasHiddenLayers = (neuronsNumber.length > 2);
		Layer inputLayer = layer(new Layer(), neuronsNumber[0]);
		ArrayList<Layer> hiddenLayers = new ArrayList<Layer>();
		for (i = 1; i < (neuronsNumber.length - 1); i++) {
			hiddenLayers.add(layer(new Layer(), neuronsNumber[i]));
		}
		Layer outputLayer = layer(new Layer(), neuronsNumber[i]);
		if (hasHiddenLayers) {
			link(inputLayer, hiddenLayers.get(0));
			for (i = 0; i < (hiddenLayers.size() - 1); i++) {
				link(hiddenLayers.get(i), hiddenLayers.get(i + 1));
			}
			link(hiddenLayers.get(i), outputLayer);
		} else {
			link(inputLayer, outputLayer);
		}
		neuralNetwork.setInputLayer(inputLayer);
		neuralNetwork.setOutputLayer(outputLayer);
		neuralNetwork.setHiddenLayers(hiddenLayers);
	}
	
	public Layer layer(Layer l, int neuronNumber) {
		double theta = random();
		for (int i = 0; i < neuronNumber; i++) {
			l.addNeuron(new Neuron(TransferFunction.STEP_FUNCTION, theta));
		}
		return l;
	}

	public void link(Layer l1, Layer l2) {
		Neuron n1;
		Neuron n2;
		Synapsis s;
		for (int i = 0; i < l1.getNeuronNumber(); i++) {
			n1 = l1.getNeuron(i);
			for (int j = 0; j < l2.getNeuronNumber(); j++) {
				n2 = l2.getNeuron(j);
				s = new Synapsis(n1, n2, random());
				n1.addOutLink(s);
				n2.addInLink(s);
			}
		}
	}

	public double random() {
		return rand.nextDouble();
	}

}
