package io.github.ekardnam.sertraline.data;

import java.util.Iterator;

public class VectorIterator implements Iterator<Double> {

	protected Vector v;
	
	protected int index = 0;
	
	public VectorIterator(Vector v) {
		this.v = v;
	}
	
	public boolean hasNext() {
		return index < v.getDimension();
	}

	public Double next() {
		return v.get(index++);
	}
	
	public int nextIndex() {
		return index++;
	}

}
