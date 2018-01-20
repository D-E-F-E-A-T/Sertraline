package io.github.ekardnam.sertraline.transfer;

class IdentityFunction implements TransferFunction {

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
