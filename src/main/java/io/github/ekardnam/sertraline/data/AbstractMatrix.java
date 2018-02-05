package io.github.ekardnam.sertraline.data;

import java.util.Iterator;
import java.util.function.Function;

/**
 * A class that represents the abstract concept of matrix
 */
public abstract class AbstractMatrix implements VectorOperation {

    /**
     * Adds another matrix to this one
     * @param other the other {@link AbstractMatrix}
     * @return a new {@link AbstractMatrix} that is the sum of this and other
     */
    public abstract AbstractMatrix add(AbstractMatrix other);

    /**
     * Multiplies the matrix by a scalar number
     * @param scalar the scalar number to multiply
     * @return a new {@link AbstractMatrix} that is the multiplication between scalar and this
     */
    public abstract AbstractMatrix multiply(double scalar);

    /**
     * Subtracts the other matrix from this
     * @param other the other matrix
     * @return a new {@link AbstractMatrix} that is the subtraction between this and other
     */
    public abstract AbstractMatrix subtract(AbstractMatrix other);

    /**
     * Divides the matrix by the given scalar
     * @param scalar the scalar to divide by
     * @return a new {@link AbstractMatrix} that is this divided by the scalar
     */
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

    @Override
    public AbstractVector output(AbstractVector input) {
        return multiply(input);
    }
}
