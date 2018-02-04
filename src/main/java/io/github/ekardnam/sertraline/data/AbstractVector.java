package io.github.ekardnam.sertraline.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public abstract class AbstractVector implements Iterable<Double>, Cloneable {

    public abstract int getDimension();

    public abstract double get(int i);

    public abstract AbstractVector add(AbstractVector other);

    public abstract AbstractVector subtract(AbstractVector other);

    public abstract AbstractVector multiply(double scalar);

    public abstract AbstractVector divide(double scalar);

    public abstract AbstractVector multiplyOneToOne(AbstractVector other);

    public abstract double dot(AbstractVector other);

    public double square() {
        return dot(this);
    }

    public double length() {
        return Math.sqrt(square());
    }

    public boolean hasEqualDimension(AbstractVector other) {
        return getDimension() == other.getDimension();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Vector)) return false;
        AbstractVector other = (AbstractVector) object;
        if (hasEqualDimension(other)) return false;
        for (int i = 0; i < getDimension(); i++) if (get(i) != other.get(i)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator<Double>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < getDimension();
            }

            @Override
            public Double next() {
                index++;
                return get(index - 1);
            }
        };
    }

    public AbstractVector map(Function<Double, Double> map) {
        double newVec[] = new double[getDimension()];
        for (int i = 0; i < getDimension(); i++) {
            newVec[i] = map.apply(get(i));
        }
        return new Vector(getDimension(), newVec);
    }

    public AbstractMatrix matrixify(int rows) {
        List<AbstractVector> rowsVec = new ArrayList();
        for (int i = 0; i < rows; i++) rowsVec.add(this);
        return new Matrix(getDimension(), rows, (AbstractVector[]) rowsVec.toArray());
    }

}
