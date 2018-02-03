package io.github.ekardnam.sertraline.activation;

//interface that describes a activation function
public interface ActivationFunction {
	
	ActivationFunction LINEAR_FUNCTION = new LinearFunction();
	ActivationFunction STEP_FUNCTION = new StepFunction();
	ActivationFunction SIGMOID_FUNCTION = new SigmoidFunction();
	ActivationFunction DEFAULT_FUNCTION = SIGMOID_FUNCTION;
	
	public double function(double x);
	
	public double derivative(double x);
	
	public boolean derivable();

}
