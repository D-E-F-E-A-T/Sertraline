package io.github.ekardnam.sertraline.data;

public class Vector extends AbstractVector implements Iterable<Double> {
	
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

	public Vector(int dimension, Double values[]) {
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

	@Override
	public Vector multiply(double scalar) {
		double newVec[] = new double[getDimension()];
		for (int i = 0; i < getDimension(); i++) newVec[i] = scalar * get(i);
		return new Vector(getDimension(), newVec);
	}

	public Vector subtract(Vector other) {
		return add(other.multiply(-1));
	}

	@Override
	public AbstractVector add(AbstractVector other) {
		return null;
	}

	@Override
	public AbstractVector subtract(AbstractVector other) {
		return null;
	}

	public Vector divide(double scalar) {
		return multiply(1 / scalar);
	}

	@Override
	public double dot(AbstractVector other) {
		return 0;
	}

	public double dot(Vector other) {
		if (!hasEqualDimension(other)) throw new IllegalArgumentException("Vectors must be equally sized");
		double result = 0;
		for (int i = 0; i < getDimension(); i++) result += get(i) * other.get(i);
		return result;
	}

	@Override
	public double length() {
		return Math.sqrt(square());
	}

	@Override
	public double square() {
		return dot(this);
	}

	@Override
	public Vector clone() {
		return new Vector(getDimension(), values);
	}

	protected void setArray(double values[]) {
		if (values.length != dimension) throw new IllegalArgumentException("Array must have vector size");
		System.arraycopy(values, 0, this.values, 0, dimension);
	}

	protected void setArray(Double values[]) {
		if (values.length != dimension) throw new IllegalArgumentException("Array must have vector size");
		for (int i = 0; i < dimension; i++) this.values[i] = values[i];
	}
}
