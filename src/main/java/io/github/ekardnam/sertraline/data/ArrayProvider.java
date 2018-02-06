package io.github.ekardnam.sertraline.data;

import java.util.Iterator;

public class ArrayProvider implements DataProvider {

	protected double inputs[];
	
	protected double outputs[];
	
	protected int inputDim;
	
	protected int outputDim;
	
	protected int i;
	
	public ArrayProvider(double inputs[], double outputs[], int inputDim, int outputDim) {
		//do some input validation here
		//TODO improve the messages
		if (inputDim == 0 || outputDim == 0) throw new IllegalArgumentException("You have to have some inputs and outputs");
		if (inputs.length % inputDim != 0 || inputs.length == 0) throw new IllegalArgumentException("Inputs array has a wrong number of elements");
		if (outputs.length % outputDim != 0 || outputs.length == 0) throw new IllegalArgumentException("Outputs array has a wrong number of elements");
		if (inputs.length / inputDim != outputs.length / outputDim) throw new IllegalArgumentException("Not as many input units as outputs'");

		//all good then
		this.inputs = inputs;
		this.outputs = outputs;
		this.inputDim = inputDim;
		this.outputDim = outputDim;
		i = 0;
	}

	protected int howManyUnits() {
		return inputs.length / inputDim;
	}

	@Override
	public int howManyInputs() {
		return inputDim;
	}

	@Override
	public int howManyOutputs() {
		return outputDim;
	}

	@Override
	public DataUnit getNext() {
		double input[] = new double[inputDim];
		double output[] = new double[outputDim];
		System.arraycopy(inputs, (i * inputDim) % inputs.length, input, 0, inputDim);
		System.arraycopy(outputs, (i * outputDim) % outputs.length, output, 0, outputDim);
		i = (i + 1) % howManyUnits();
		return new DataUnit(new Vector(inputDim, input), new Vector(outputDim, output));
	}

	@Override
	public Iterator<DataUnit> iterator() {
		return new Iterator<DataUnit>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < howManyUnits();
			}

			@Override
			public DataUnit next() {
				index++;
				return getNext();
			}
		};
	}
}
