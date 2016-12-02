package io.github.luc99a.neuralnetwork.transfer;

public class IdentityFunction implements TransferFunction {

	public double T(double x) {
		return x;
	}

	public double dTdx(double x) {
		return 1;
	}

	public boolean derivable() {
		return true;
	}

}
