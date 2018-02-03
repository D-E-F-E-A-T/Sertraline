package io.github.ekardnam.sertraline.data;

public class DataUnit {

	protected Vector inputs;

	protected Vector outputs;
	
	public DataUnit(Vector inputs, Vector outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}

	public Vector getInputs() { return inputs; }

	public Vector getOutputs() { return outputs; }

}
