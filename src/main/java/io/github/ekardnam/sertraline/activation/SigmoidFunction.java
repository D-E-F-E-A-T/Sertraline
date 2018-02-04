package io.github.ekardnam.sertraline.activation;

class SigmoidFunction extends ActivationFunction {

	public double function(double x) {
		return (1 / (1 + Math.exp(-x)));
	}

	public double derivative(double x) {
		return (function(x) * (1 - function(x)));
	}

	public boolean derivable() {
		return true;
	}

}
