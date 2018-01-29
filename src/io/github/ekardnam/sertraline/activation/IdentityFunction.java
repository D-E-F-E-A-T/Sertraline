package io.github.ekardnam.sertraline.activation;

public class IdentityFunction implements ActivationFunction {

	public double function(double x) {
		return x;
	}

	public double derivative(double x) {
		return 1;
	}

	public boolean derivable() {
		return true;
	}

}
