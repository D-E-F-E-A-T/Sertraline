package io.github.luc99a.neuralnetwork.learning;

import io.github.luc99a.neuralnetwork.NeuralNetwork;
import io.github.luc99a.neuralnetwork.objects.Synapsis;
import io.github.luc99a.neuralnetwork.transfer.TransferFunction;

//this class implements the Widrow Hoff algorithm for the perceptron
public class PerceptronWidrowHoffAlgorithm implements LearningAlgorithm {
	
		//learning rate, default is 0.5
		private double eta = 0.5;
		
		//max epochs, default 1024
		private int maxEpochs = 1024;
		
		//max error, default 0.00001
		private double error = 0.00001;
		
		//training set
		private double inputs[];
		private double outputs[];
		
		public PerceptronWidrowHoffAlgorithm(double inputs[], double outputs[]) {
			this.inputs = inputs;
			this.outputs = outputs;
		}
		
		public PerceptronWidrowHoffAlgorithm(double inputs[], double outputs[], double eta) {
			this(inputs, outputs);
			setEta(eta);
		}
		
		public PerceptronWidrowHoffAlgorithm(double inputs[], double outputs[], double eta, int maxEpochs) {
			this(inputs, outputs, eta);
			this.maxEpochs = maxEpochs;
		}
		
		public PerceptronWidrowHoffAlgorithm(double inputs[], double outputs[], double eta, int maxEpochs, double error) {
			this(inputs, outputs, eta, maxEpochs);
			this.error = error;
		}
		
		public PerceptronWidrowHoffAlgorithm(double eta) {
			setEta(eta);
		}
		
		public PerceptronWidrowHoffAlgorithm(double eta, int maxEpochs) {
			this(eta);
			this.maxEpochs = maxEpochs;
		}
		
		public PerceptronWidrowHoffAlgorithm(double eta, int maxEpochs, double error) {
			this(eta, maxEpochs);
			this.error = error;
		}
		
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

		//training function, this algorithm is only for perceptrons
		public void train(NeuralNetwork neuralNetwork) {
			int i;
			Synapsis s;
			int inputsNumber = neuralNetwork.getInputNumber();
			double y = 0;
			double currentError = 0;
			double dEdy = 0;
			double dEdw[] = new double[inputsNumber + 1];
			double currentInputs[] = new double[inputsNumber];
			double delta = 0;
			int epoch = 0;
			TransferFunction transferFunction = neuralNetwork.getOutputLayer().getNeuron(0).getTransferFunction();
			if (!transferFunction.derivable()) {
				transferFunction = TransferFunction.IDENTITY_FUNCTION;
			}
			do {
				currentError = 0;
				for (i = 0; i < (inputsNumber + 1); i++) {
					dEdw[i] = 0;
				}
				for (int t = 0; t < (inputs.length / inputsNumber); t++) {
					System.arraycopy(inputs, (t * inputsNumber), currentInputs, 0, currentInputs.length);
					y = neuralNetwork.runNetwork(currentInputs)[0];
					dEdy = -(outputs[t] - y);
					for (i = 0; i < inputsNumber; i++) {
						dEdw[i] += dEdy * currentInputs[i] * transferFunction.dTdx(y);
					}
					dEdw[i] += -(dEdy * transferFunction.dTdx(y));
					currentError += (dEdy * dEdy) / 2;
				}
				for (i = 0; i < inputsNumber; i++) {
					s = neuralNetwork.getOutputLayer().getNeuron(0).getInLink(i);
					delta = -(eta * dEdw[i]);
					s.w += delta;
				}
				neuralNetwork.getOutputLayer().getNeuron(0).theta += (-(eta * dEdw[i]));
				epoch++;
			} while(currentError > error && epoch < maxEpochs);
		}

		public boolean prepare(NeuralNetwork neuralNetwork) {
			return neuralNetwork.getType() == NeuralNetwork.Type.PERCEPTRON;
		}
		
}
