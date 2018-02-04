package io.github.ekardnam.sertraline.data;

public interface DataProvider extends Iterable<DataUnit> {
	
	public DataUnit getNext();

}
