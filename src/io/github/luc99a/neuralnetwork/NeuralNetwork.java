package io.github.luc99a.neuralnetwork;

import io.github.luc99a.neuralnetwork.builder.DefaultNetworkBuilder;
import io.github.luc99a.neuralnetwork.builder.NetworkBuilder;
import io.github.luc99a.neuralnetwork.learning.LearningAlgorithm;
import io.github.luc99a.neuralnetwork.objects.Layer;
import io.github.luc99a.neuralnetwork.objects.NetworkObject;
import io.github.luc99a.neuralnetwork.objects.Neuron;
import io.github.luc99a.neuralnetwork.objects.Synapsis;
import io.github.luc99a.neuralnetwork.transfer.StepFunction;
import io.github.luc99a.neuralnetwork.transfer.TransferFunction;

import java.util.ArrayList;

//a class that represents a neural network
public class NeuralNetwork {
	
	public static enum Type {
		PERCEPTRON,
		GENERIC
	}
	
	//the transfer function used
	protected TransferFunction transferFunction = new StepFunction();
	
	//the network builder
	protected NetworkBuilder networkBuilder = new DefaultNetworkBuilder();
	
	//the learning algorithm used
	protected LearningAlgorithm learningAlgorithm;
	
	//input layer of the network
	protected Layer inputLayer;
	
	//output layer of the network
	protected Layer outputLayer;
	
	//hidden layers of the network
	protected ArrayList<Layer> hiddenLayers;
	
	//helper attributes
	protected int inputNumber;
	protected int outputNumber;
	protected boolean preparedLearningAlgorithm = false;
	protected int neuronsNumber[];
	
	public NeuralNetwork(int... neuronsNumber) {
		if (neuronsNumber.length < 2) {
			throw new IllegalArgumentException("At least two layers are needed");
		}
		this.neuronsNumber = neuronsNumber;
		inputNumber = this.neuronsNumber[0];
		outputNumber = this.neuronsNumber[this.neuronsNumber.length - 1];
	}
	
	public NeuralNetwork(LearningAlgorithm learningAlgorithm, int ... neuronsNumber) {
		this(neuronsNumber);
		setLearningAlgorithm(learningAlgorithm);
	}
	
	public void buildNetwork() {
		networkBuilder.build(this, neuronsNumber);
	}
	
	public void trainNetwork() {
		if (preparedLearningAlgorithm) {
			learningAlgorithm.train(this);
		}
	}
	
	//runs the network
	public double[] runNetwork(double ... inputs) {
		double outputs[] = new double[outputNumber];
		int i = 0;
		prepareInputLayer(inputs);
		for (Layer l : hiddenLayers) {
			l.runLayer();
		}
		outputLayer.runLayer();
		for (Neuron n : outputLayer.getNeurons()) {
			outputs[i] = n.transfer;
			i++;
		}
		return outputs;
	}
	
	public TransferFunction getTransferFunction() {
		return transferFunction;
	}
	
	public int getInputNumber() {
		return inputNumber;
	}
	
	public int getOutputNumber() {
		return outputNumber;
	}
	
	public Layer getInputLayer() {
		return inputLayer;
	}
	
	public Layer getOutputLayer() {
		return outputLayer;
	}
	
	public ArrayList<Layer> getHiddenLayers() {
		return hiddenLayers;
	}
	
	public void setInputLayer(Layer inputLayer) {
		this.inputLayer = inputLayer;
	}
	
	public void setOutputLayer(Layer outputLayer) {
		this.outputLayer = outputLayer;
	}
	
	public void setHiddenLayers(ArrayList<Layer> hiddenLayers) {
		this.hiddenLayers = hiddenLayers;
	}
	
	public void setLearningAlgorithm(LearningAlgorithm learningAlgorithm) {
		this.learningAlgorithm = learningAlgorithm;
		if (!this.learningAlgorithm.prepare(this)) {
			throw new RuntimeException("Learning Algorithm preparation failed");
		}
		preparedLearningAlgorithm = true;
	}
	
	public void setNetworkBuilder(NetworkBuilder networkBuilder) {
		this.networkBuilder = networkBuilder;
	}
	
	public Type getType() {
		return Type.GENERIC;
	}
	
	public void prepareInputLayer(double inputs[]) {
		int i = 0;
		if (inputs.length != inputLayer.getNeuronNumber()) {
			throw new IllegalArgumentException("Invalid inputs");
		}
		for (Neuron n : inputLayer.getNeurons()) {
			n.transfer = inputs[i];
			i++;
		}
	}
	
	public void forEachLayer(final NeuralNetworkCallback neuralNetworkCallback) {
		neuralNetworkCallback.run(inputLayer);
		for (Layer l : hiddenLayers) {
			neuralNetworkCallback.run(l);
		}
		neuralNetworkCallback.run(outputLayer);
	}
	
	public void forEachNeuron(final NeuralNetworkCallback neuralNetworkCallback) {
		forEachLayer(new NeuralNetworkCallback() {
			public void run(NetworkObject networkObject) {
				Layer l = (Layer) networkObject;
				for (Neuron n : l.getNeurons()) {
					neuralNetworkCallback.run(n);
				}
			}
		});
	}
	
	public void forEachSynapsis(final NeuralNetworkCallback neuralNetworkCallback) {
		forEachNeuron(new NeuralNetworkCallback() {
			public void run(NetworkObject networkObject) {
				Neuron n = (Neuron) networkObject;
				for (Synapsis s : n.getInLinks()) {
					neuralNetworkCallback.run(s);
				}
			}
		});
	}
	
	public void allocLayerData(final int size) {
		forEachLayer(new NeuralNetworkCallback() {
			public void run(NetworkObject networkObject) {
				networkObject.data = new double[size];
			}
		});
	}
	
	public void allocNeuronData(final int size) {
		forEachNeuron(new NeuralNetworkCallback() {
			public void run(NetworkObject networkObject) {
				networkObject.data = new double[size];
			}
		});
	}
	
	public void allocSynapsisData(final int size) {
		forEachSynapsis(new NeuralNetworkCallback() {
			public void run(NetworkObject networkObject) {
				networkObject.data = new double[size];
			}
		});
	}
	
	
}
