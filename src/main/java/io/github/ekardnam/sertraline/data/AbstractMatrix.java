package io.github.ekardnam.sertraline.data;

import java.util.Objects;
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

    /**
     * Multiplies the matrix by an {@link AbstractVector}
     * @param vector the {@link AbstractVector}
     * @return the result of the multiplication
     */
    public abstract AbstractVector multiply(AbstractVector vector);

    /**
     * Multiplies the matrix by another matrix
     * @param other the other matrix
     * @return the result of the multiplication
     */
    public abstract AbstractMatrix multiply(AbstractMatrix other);

    /**
     * Multiplies each value of the matrix by the other corresponding one and returns a matrix of those values
     * @param other the other matrix
     * @return the result
     */
    public abstract AbstractMatrix multiplyOneToOne(AbstractMatrix other);

    public abstract double get(int row, int col);

    public abstract int getRowDimension();

    public abstract int getColDimension();

    public int howManyRows() { return getColDimension(); }

    public int howManyCols() { return getRowDimension(); }

    public abstract AbstractMatrix transpose();

    public abstract AbstractMatrix copy();

    public abstract Iterable<AbstractVector> rows();

    public abstract Iterable<AbstractVector> cols();

    public abstract AbstractMatrix map(Function<Double, Double> map);

    public abstract AbstractVector getRow(int index);

    public abstract AbstractVector getCol(int index);

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AbstractMatrix)) return false;
        AbstractMatrix other = (AbstractMatrix) object;
        if (getRowDimension() != other.getRowDimension() || getColDimension() != other.getColDimension()) return false;
        for (int i = 0; i < howManyRows(); i++) {
            if (!getRow(i).equals(other.getRow(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public AbstractVector output(AbstractVector input) {
        return multiply(input);
    }
}
