package io.github.ekardnam.sertraline.data;

import com.sun.istack.internal.NotNull;

import java.util.function.Function;

public class MutableVector extends Vector {

    public MutableVector(int dimension) {
        super(dimension);
    }

    public MutableVector(int dimension, double values[]) {
        super(dimension, i -> values[i]);
    }

    public MutableVector(int dimension, Function<Integer, Double> init) {
        super(dimension, init);
    }

    public void set(@NotNull AbstractVector vector) {
        if (vector.getDimension() != dimension) throw new IllegalArgumentException("Must have equal dimensions");
        for (int i = 0; i < dimension; i++) values[i] = get(i);
    }

    @Override
    public AbstractVector add(AbstractVector other) {
        AbstractVector sum = super.add(other);
        set(sum);
        return sum;
    }

    @Override
    public AbstractVector multiply(double scalar) {
        AbstractVector prod = super.multiply(scalar);
        set(prod);
        return prod;
    }

    @Override
    public AbstractVector subtract(AbstractVector other) {
        AbstractVector diff = super.subtract(other);
        set(diff);
        return diff;
    }

    @Override
    public AbstractVector divide(double scalar) {
        AbstractVector div = super.divide(scalar);
        set(div);
        return div;
    }

    @Override
    public AbstractVector multiplyOneToOne(AbstractVector other) {
        AbstractVector result = super.multiplyOneToOne(other);
        set(result);
        return result;
    }

    @Override
    public AbstractVector copy() {
        return new MutableVector(getDimension(), values);
    }

    @Override
    public AbstractVector map(Function<Double, Double> map) {
        AbstractVector mapped = super.map(map);
        set(mapped);
        return mapped;
    }

    public void zeroIt() { map(v -> (double) 0); }

}
