package io.github.ekardnam.sertraline.transfer;

class SigmoidFunction implements TransferFunction {

	public double T(double x) {
		return (1 / (1 + Math.exp(-x)));
	}

	public double dTdx(double x) {
		return (T(x) * (1 - T(x)));
	}

	public boolean derivable() {
		return true;
	}

}
