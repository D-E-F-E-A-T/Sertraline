package io.github.ekardnam.sertraline.data;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractVector implements Iterable<Double>, Cloneable {

    public abstract int getDimension();

    public abstract double get(int i);

    public abstract AbstractVector add(AbstractVector other);

    public abstract AbstractVector subtract(AbstractVector other);

    public abstract AbstractVector multiply(double scalar);

    public abstract AbstractVector divide(double scalar);

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

}
