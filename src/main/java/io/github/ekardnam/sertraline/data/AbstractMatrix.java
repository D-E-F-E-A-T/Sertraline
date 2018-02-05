package io.github.ekardnam.sertraline.data;

import java.util.Iterator;
import java.util.function.Function;

public abstract class AbstractMatrix {

    public abstract AbstractMatrix add(AbstractMatrix other);

    public abstract AbstractMatrix multiply(double scalar);

    public abstract AbstractMatrix subtract(AbstractMatrix other);

    public abstract AbstractMatrix divide(double scalar);

    public abstract AbstractVector multiply(AbstractVector vector);

    public abstract AbstractMatrix multiplyOneToOne(AbstractMatrix other);

    public abstract double get(int i, int j);

    public abstract int getXDimension();

    public abstract int getYDimension();

    public abstract AbstractMatrix transpose();

    public abstract AbstractMatrix copy();

    public abstract Iterable<AbstractVector> rows();

    public abstract Iterable<AbstractVector> cols();

    public abstract AbstractMatrix map(Function<Double, Double> map);

}
