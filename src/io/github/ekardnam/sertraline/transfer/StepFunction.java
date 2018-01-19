package io.github.ekardnam.sertraline.transfer;

//step function implementation
public class StepFunction implements TransferFunction {

	public double T(double x) {
		if (x > 0) {
			return 1;
		}
		return 0;
	}

	public double dTdx(double x) {
		return 1;
	}

	public boolean derivable() {
		return false;
	}

}
