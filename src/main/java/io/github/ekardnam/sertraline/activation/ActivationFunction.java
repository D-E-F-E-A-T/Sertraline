package io.github.ekardnam.sertraline.activation;

//interface that describes a activation function
public interface ActivationFunction {
	
	public static final ActivationFunction IDENTITY_FUNCTION = new IdentityFunction();
	public static final ActivationFunction STEP_FUNCTION = new StepFunction();
	public static final ActivationFunction SIGMOID_FUNCTION = new SigmoidFunction();
	public static final ActivationFunction DEFAULT_FUNCTION = SIGMOID_FUNCTION;
	
	public double function(double x);
	
	public double derivative(double x);
	
	public boolean derivable();

}
