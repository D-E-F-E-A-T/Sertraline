package io.github.ekardnam.sertraline.data;

import java.util.function.Function;

public class Vector extends AbstractVector implements Iterable<Double> {
	
	protected int dimension;
	
	protected double values[];
	
	public Vector(int dimension) {
		this(dimension, i -> (double) 0);
	}
	
	public Vector(int dimension, double values[]) {
		this(dimension, i -> values[i]);
	}

	public Vector(int dimension, Function<Integer, Double> init) {
		this.dimension = dimension;
		values = new double[dimension];
		for (int i = 0; i < dimension; i++) {
			values[i] = init.apply(i);
		}
	}

	@Override
	public int getDimension() {
		return dimension;
	}

	@Override
	public double get(int i) {
		return values[i];
	}

	@Override
	public AbstractVector add(AbstractVector other) {
		if (!hasEqualDimension(other)) throw new IllegalArgumentException("Vector must be equally sized");
		double sum[] = new double[getDimension()];
		for (int i = 0; i < getDimension(); i++) sum[i] = get(i) + other.get(i);
		return new Vector(getDimension(), sum);
	}

	@Override
	public AbstractVector multiply(double scalar) {
		double newVec[] = new double[getDimension()];
		for (int i = 0; i < getDimension(); i++) newVec[i] = scalar * get(i);
		return new Vector(getDimension(), newVec);
	}

	@Override
	public AbstractVector subtract(AbstractVector other) {
		return add(other.multiply(-1));
	}

	@Override
	public AbstractVector divide(double scalar) {
		return multiply(1 / scalar);
	}

	@Override
	public AbstractVector multiplyOneToOne(AbstractVector other) {
		if (!hasEqualDimension(other)) throw new IllegalArgumentException("Vectors must be equally sized");
		double newVec[] = new double[getDimension()];
		for (int i = 0; i < getDimension(); i++) {
			newVec[i] = get(i) + other.get(i);
		}
		return new Vector(getDimension(), newVec);
	}

	@Override
	public double dot(AbstractVector other) {
		return 0;
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
	public AbstractVector copy() {
		return new Vector(getDimension(), values);
	}

	@Override
	public AbstractVector map(Function<Double, Double> map) {
		double newVec[] = new double[getDimension()];
		for (int i = 0; i < getDimension(); i++) {
			newVec[i] = map.apply(get(i));
		}
		return new Vector(getDimension(), newVec);
	}

	@Override
	public AbstractMatrix matrixify(int rows) {
		AbstractVector rowsVec[] = new AbstractVector[rows];
		for (int i = 0; i < rows; i++) rowsVec[i] = copy();
		return new Matrix(getDimension(), rows, rowsVec);
	}

}
