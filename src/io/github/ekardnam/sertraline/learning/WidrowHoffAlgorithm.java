package io.github.ekardnam.sertraline.learning;

import io.github.ekardnam.sertraline.NeuralNetwork;
import io.github.ekardnam.sertraline.Perceptron;
import io.github.ekardnam.sertraline.data.DataProvider;
import io.github.ekardnam.sertraline.objects.Synapsis;
import io.github.ekardnam.sertraline.transfer.TransferFunction;

//this class implements the Widrow Hoff algorithm for the perceptron
public class WidrowHoffAlgorithm implements LearningAlgorithm {
	
		//learning rate, default is 0.5
		protected double eta = 0.5;
		
		//max epochs, default 1024
		protected int maxEpochs = 1024;
		
		//max error, default 0.00001
		protected double error = 0.00001;
		
		//training set
		protected DataProvider provider; 
		
		public WidrowHoffAlgorithm(DataProvider provider) {
			this.provider = provider;
		}
		
		public WidrowHoffAlgorithm(DataProvider provider, double eta) {
			this(provider);
			setEta(eta);
		}
		
		public WidrowHoffAlgorithm(DataProvider provider, double eta, int maxEpochs) {
			this(provider, eta);
			this.maxEpochs = maxEpochs;
		}
		
		public WidrowHoffAlgorithm(DataProvider provider, double eta, int maxEpochs, double error) {
			this(provider, eta, maxEpochs);
			this.error = error;
		}
		
		public WidrowHoffAlgorithm(double eta) {
			setEta(eta);
		}
		
		public WidrowHoffAlgorithm(double eta, int maxEpochs) {
			this(eta);
			this.maxEpochs = maxEpochs;
		}
		
		public WidrowHoffAlgorithm(double eta, int maxEpochs, double error) {
			this(eta, maxEpochs);
			this.error = error;
		}
		
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

		
		//TODO rewrite all of this stuff
		public void train(NeuralNetwork neuralNetwork) {
			//was horrible code, will rewrite it
		}

		public boolean prepare(NeuralNetwork neuralNetwork) {
			return neuralNetwork instanceof Perceptron;
		}
		
}
