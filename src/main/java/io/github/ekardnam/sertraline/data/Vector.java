package io.github.ekardnam.sertraline.data;

import java.util.Iterator;
import java.util.Objects;

public class Vector implements Iterable<Double>, Cloneable {
	
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

	public Vector add(Vector other) {
		if (!hasEqualDimension(other)) throw new IllegalArgumentException("Vector must be equally sized");
		double sum[] = new double[getDimension()];
		for (int i = 0; i < getDimension(); i++) sum[i] = get(i) + other.get(i);
		return new Vector(getDimension(), sum);
	}

	public Vector multiply(double scalar) {
		double newVec[] = new double[getDimension()];
		for (int i = 0; i < getDimension(); i++) newVec[i] = scalar * get(i);
		return new Vector(getDimension(), newVec);
	}

	public Vector subtract(Vector other) {
		return add(other.multiply(-1));
	}

	public Vector divide(double scalar) {
		return multiply(1 / scalar);
	}

	public boolean hasEqualDimension(Vector other) {
		return getDimension() == other.getDimension();
	}

	@Override
	public Iterator<Double> iterator() {
		return new VectorIterator(this);
	}

	@Override
	public Vector clone() {
		return new Vector(dimension, values);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Vector)) return false;
		Vector other = (Vector) object;
		if (hasEqualDimension(other)) return false;
		for (int i = 0; i < dimension; i++) if (get(i) != other.get(i)) return false;
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dimension, values);
	}

	protected void setArray(double values[]) {
		if (values.length != dimension) throw new IllegalArgumentException("Array must have vector size");
		System.arraycopy(values, 0, this.values, 0, dimension);
	}
}
