package io.github.ekardnam.sertraline;

import java.util.ArrayList;

import io.github.ekardnam.sertraline.builder.DefaultNetworkBuilder;
import io.github.ekardnam.sertraline.builder.NetworkBuilder;
import io.github.ekardnam.sertraline.data.Vector;
import io.github.ekardnam.sertraline.data.VectorIterator;
import io.github.ekardnam.sertraline.learning.LearningAlgorithm;
import io.github.ekardnam.sertraline.objects.Layer;
import io.github.ekardnam.sertraline.objects.NetworkObject;
import io.github.ekardnam.sertraline.objects.Neuron;
import io.github.ekardnam.sertraline.objects.Synapsis;

//a class that represents a neural network
public class NeuralNetwork {
	
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
	
	public Vector runNetwork(double ... inputs) {
		//validate arguments
		if (inputs.length != inputNumber) throw new IllegalArgumentException("Illegal input, wrong number of parameters");
		
		//all good
		double outputs[] = new double[outputNumber];
		prepareInputLayer(new Vector(inputNumber, inputs));
		for (Layer l : hiddenLayers) {
			l.runLayer();
		}
		outputLayer.runLayer();
		
		Vector output = new Vector(outputNumber);
		VectorIterator iterator = (VectorIterator) output.iterator();
		outputLayer.forEachNeuron(new NeuralNetworkCallback() {
			public void run(NetworkObject o) {
				Neuron n = (Neuron) o;
				int i = iterator.nextIndex();
				output.set(i, n.transfer);
			}
		});
		return output;
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
	
	public void prepareInputLayer(final Vector input) {
		if (input.getDimension() != inputLayer.getNeuronNumber()) throw new IllegalArgumentException("Invalid input");
		
		VectorIterator iterator = (VectorIterator) input.iterator();	
		inputLayer.forEachNeuron(new NeuralNetworkCallback() {
			public void run(NetworkObject o) {
				Neuron n = (Neuron) o;
				n.transfer = iterator.next();
			}
		});
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
