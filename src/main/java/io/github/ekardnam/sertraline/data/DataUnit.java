package io.github.ekardnam.sertraline.data;

import com.sun.istack.internal.NotNull;

public class DataUnit {

	protected Vector inputs;

	protected Vector outputs;
	
	public DataUnit(@NotNull Vector inputs, @NotNull Vector outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}

	public Vector getInputs() { return inputs; }

	public Vector getOutputs() { return outputs; }

}
