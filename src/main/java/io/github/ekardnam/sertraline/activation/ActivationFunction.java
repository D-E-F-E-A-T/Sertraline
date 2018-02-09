package io.github.ekardnam.sertraline.activation;

import io.github.ekardnam.sertraline.data.AbstractVector;

public abstract class ActivationFunction {
	
	public static final ActivationFunction LINEAR_FUNCTION = new LinearFunction();
	public static final ActivationFunction STEP_FUNCTION = new StepFunction();
	public static final ActivationFunction SIGMOID_FUNCTION = new SigmoidFunction();
	public static final ActivationFunction DEFAULT_FUNCTION = SIGMOID_FUNCTION;
	
	public abstract double function(double x);
	
	public abstract double derivative(double x);
	
	public abstract boolean derivable();

	public AbstractVector function(AbstractVector vector) {
		return vector.map(value -> function(value));
	}

	public AbstractVector derivative(AbstractVector vector) {
		if (!derivable()) return null;
		return vector.map(value -> derivative(value));
	}

}
