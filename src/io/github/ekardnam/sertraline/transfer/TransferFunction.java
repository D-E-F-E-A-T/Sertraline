package io.github.luc99a.neuralnetwork.transfer;

//interface that describes a transfer function
public interface TransferFunction {
	
	public static final TransferFunction IDENTITY_FUNCTION = new IdentityFunction();
	public static final TransferFunction STEP_FUNCTION = new StepFunction();
	public static final TransferFunction SIGMOID_FUNCTION = new SigmoidFunction();
	
	public double T(double x);
	
	public double dTdx(double x);
	
	public boolean derivable();

}
