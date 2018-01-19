package io.github.luc99a.neuralnetwork.learning;

import io.github.luc99a.neuralnetwork.NeuralNetwork;
import io.github.luc99a.neuralnetwork.objects.Layer;
import io.github.luc99a.neuralnetwork.objects.Neuron;
import io.github.luc99a.neuralnetwork.objects.Synapsis;
import io.github.luc99a.neuralnetwork.transfer.TransferFunction;

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

	public void train(NeuralNetwork neuralNetwork) {
		double delta = 0;
		int epoch = 0;
		double currentError;
		int i;
		int j;
		int inputsNumber = neuralNetwork.getInputNumber();
		int outputsNumber = neuralNetwork.getOutputNumber();
		int hiddenLayersNumber = neuralNetwork.getHiddenLayers().size();
		double currentInputs[] = new double[inputsNumber];
		double y[] = new double[outputsNumber];
		Layer previousLayer;
		Synapsis s;
		TransferFunction transfer = neuralNetwork.getTransferFunction();
		if (!transfer.derivable()) {
			transfer = TransferFunction.IDENTITY_FUNCTION;
		}
		do {
			currentError = 0;
			for (int t = 0; t < (inputs.length / inputsNumber); t++) {
				System.arraycopy(inputs, (t * inputsNumber), currentInputs, 0, inputsNumber);
				y = neuralNetwork.runNetwork(currentInputs);
				i = 0;
				for (Neuron n : neuralNetwork.getOutputLayer().getNeurons()) {
					n.data[DEDY] = -(outputs[(t * outputsNumber) + i] - n.transfer);
					error += Math.pow(n.data[DEDY], 2);
					i++;
				}
				for (i = (hiddenLayersNumber - 1); i >= 0; i--) {
					if (i == (hiddenLayersNumber - 1)) {
						previousLayer = neuralNetwork.getOutputLayer();
					} else {
						previousLayer = neuralNetwork.getHiddenLayers().get(i - 1);
					}
					for (Neuron n1 : neuralNetwork.getHiddenLayers().get(i).getNeurons()) {
						n1.data[DEDY] = 0;
						for (Neuron n2 : previousLayer.getNeurons()) {
							s = n1.getOutLinkTo(n2);
							n1.data[DEDY] += n2.data[DEDY] * (transfer.dTdx(n2.transfer) + s.w);
						}
						n1.data[DEDY] += n1
					}
				}
			}
		} while(currentError > error && epoch < maxEpochs);
	}

}
