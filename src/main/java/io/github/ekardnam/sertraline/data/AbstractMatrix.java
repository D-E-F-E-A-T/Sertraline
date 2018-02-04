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

    public Iterable<AbstractVector> rows() {
        return new Iterable<AbstractVector>() {
            @Override
            public Iterator<AbstractVector> iterator() {
                return new Iterator<AbstractVector>() {
                    private int index = 0;

                    @Override
                    public boolean hasNext() {
                        return index < getYDimension();
                    }

                    @Override
                    public AbstractVector next() {
                        double vector[] = new double[getXDimension()];
                        for (int i = 0; i < getXDimension(); i++) {
                            vector[i] = get(i, index);
                        }
                        index++;
                        return new Vector(getXDimension(), vector);
                    }
                };
            }
        };
    }

    public Iterable<AbstractVector> cols() {
        return transpose().rows();
    }

    public AbstractMatrix map(Function<Double, Double> map) {
        double newMat[][] = new double[getXDimension()][getYDimension()];
        for (int i = 0; i < getXDimension(); i++) {
            for (int j = 0; j < getYDimension(); j++) {
                newMat[i][j] = map.apply(get(i, j));
            }
        }
        return new Matrix(getXDimension(), getYDimension(), newMat);
    }

}
