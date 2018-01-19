package io.github.ekardnam.sertraline.data;

import java.util.Iterator;

public class Vector implements Iterable<Double> {
	
	protected int dimension;
	
	protected double values[];
	
	public Vector(int dimension) {
		this.dimension = dimension;
		values = new double[dimension];
	}
	
	public Vector(int dimension, double values[]) {
		this(dimension);
		setArray(values);
	}
	
	public int getDimension() {
		return dimension;
	}
	
	public double get(int i) {
		return values[i];
	}
	
	public void set(int i, double v) {
		values[i] = v;
	}
	
	public void setArray(double values[]) {
		if (values.length != dimension) throw new IllegalArgumentException("Array must have vector size");
		System.arraycopy(values, 0, this.values, 0, dimension);
	}

	public Iterator<Double> iterator() {
		return new VectorIterator(this);
	}

}
