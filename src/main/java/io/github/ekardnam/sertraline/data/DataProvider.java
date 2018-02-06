package io.github.ekardnam.sertraline.data;

public interface DataProvider extends Iterable<DataUnit> {

	public int howManyInputs();

	public int howManyOutputs();

	public DataUnit getNext();

}
