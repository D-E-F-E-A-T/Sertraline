package io.github.ekardnam.sertraline.data;

import com.sun.istack.internal.NotNull;

public class DataUnit {

	protected AbstractVector inputs;

	protected AbstractVector outputs;
	
	public DataUnit(@NotNull AbstractVector inputs, @NotNull AbstractVector outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}

	public AbstractVector getInputs() { return inputs; }

	public AbstractVector getOutputs() { return outputs; }

}
