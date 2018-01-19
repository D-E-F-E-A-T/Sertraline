package io.github.ekardnam.sertraline.learning;

import io.github.ekardnam.sertraline.NeuralNetwork;

public class BackpropagationAlgorithm implements LearningAlgorithm {
	
	private static final int DEDY = 0;
	private static final int DEDW = 0;
	private static final int SYNAPSIS_ALLOC = 1;
	private static final int NEURON_ALLOC = 1;
	
	//learning rate, default is 0.5
	private double eta = 0.5;
	
	//max epochs, default 1024
	private int maxEpochs = 1024;
	
	//max error, default 0.00001
	private double error = 0.00001;
	
	//training set
	private double inputs[];
	private double outputs[];
	
	//set the learning rate
	public void setEta(double eta) {
		if (eta >= 1 || eta < 0) {
			throw new IllegalArgumentException("Eta must be positive and less than 1");
		}
		this.eta = eta;
	}
	
	public void setError(double error) {
		this.error = error;
	}
	
	public void setMaxEpochs(int maxEpochs) {
		this.maxEpochs = maxEpochs;
	}

	public boolean prepare(NeuralNetwork neuralNetwork) {
		neuralNetwork.allocNeuronData(NEURON_ALLOC);
		neuralNetwork.allocSynapsisData(SYNAPSIS_ALLOC);
		return true;
	}

	//TODO implementation
	public void train(NeuralNetwork neuralNetwork) {

	}

}
