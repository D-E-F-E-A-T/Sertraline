package io.github.ekardnam.sertraline.data;

public abstract class AbstractMatrix implements Cloneable {

    public abstract AbstractMatrix add(AbstractMatrix other);

    public abstract AbstractMatrix multiply(double scalar);

    public abstract AbstractMatrix subtract(AbstractMatrix other);

    public abstract AbstractMatrix divide(double scalar);

    public abstract double get(int i, int j);

    public abstract int getXDimension();

    public abstract int getYDimension();

    public abstract AbstractMatrix transpose();

}
