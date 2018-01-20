package io.github.ekardnam.sertraline.transfer;

//interface that describes a transfer function
public interface TransferFunction {
	
	public static final TransferFunction IDENTITY_FUNCTION = new IdentityFunction();
	public static final TransferFunction STEP_FUNCTION = new StepFunction();
	public static final TransferFunction SIGMOID_FUNCTION = new SigmoidFunction();
	public static final TransferFunction DEFAULT_FUNCTION = SIGMOID_FUNCTION;
	
	public double function(double x);
	
	public double derivative(double x);
	
	public boolean derivable();

}
