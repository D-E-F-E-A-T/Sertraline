package io.github.ekardnam.sertraline.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Matrix extends AbstractMatrix {

    protected int x;

    protected int y;

    protected double values[][];

    public Matrix(int x, int y) {
        this.x = x;
        this.y = y;
        values = new double[x][y];
    }

    public Matrix(int x, int y, double values[][]) {
       this(x, y);
        setArray(values);
    }

    public Matrix(int x, int y, Double values[][]) {
        this(x, y);
        setArray(values);
    }

    public Matrix(int x, int y,  AbstractVector rows[]) {
        //TODO("Better illegal messages")
        if (rows.length != y) throw new IllegalArgumentException("Illegal");
        for (int i = 0; i < rows.length; i++) if (rows[i].getDimension() != x) throw new IllegalArgumentException("Illegal");

        this.x = x;
        this.y = y;
        values = new double[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                values[i][j] = rows[i].get(j);
            }
        }
    }

    @Override
    public int getXDimension() { return x; }

    @Override
    public int getYDimension() { return y; }

    @Override
    public AbstractMatrix add(AbstractMatrix other) {
        double newMat[][] = new double[getXDimension()][getYDimension()];
        for (int i = 0; i < getXDimension(); i++) for (int j = 0; j < getYDimension(); j++) newMat[i][j] = get(i, j) + other.get(i, j);
        return new Matrix(getXDimension(), getYDimension(), newMat);
    }

    @Override
    public AbstractMatrix multiply(double scalar) {
        double newMat[][] = new double[getXDimension()][getYDimension()];
        for (int i = 0; i < getXDimension(); i++) for (int j = 0; j < getYDimension(); j++) newMat[i][j] = scalar * get(i, j);
        return new Matrix(getXDimension(), getYDimension(), newMat);
    }

    @Override
    public AbstractMatrix subtract(AbstractMatrix other) {
        return add(other.multiply(-1));
    }

    @Override
    public AbstractMatrix divide(double scalar) {
        return multiply(1 / scalar);
    }

    @Override
    public AbstractVector multiply(AbstractVector vector) {
        if (vector.getDimension() != y) throw new IllegalArgumentException("Illegal");
        List<Double> newVec = new ArrayList();
        for (AbstractVector row : rows()) {
            newVec.add(row.dot(vector));
        }
        return new Vector(vector.getDimension(), (Double[]) newVec.toArray());
    }

    @Override
    public double get(int i, int j) {
        return values[i][j];
    }

    @Override
    public AbstractMatrix transpose() {
        double newMat[][] = new double[getXDimension()][getYDimension()];
        for (int i = 0; i < getXDimension(); i++) for (int j = 0; j < getYDimension(); j++) newMat[j][i] = get(i, j);
        return new Matrix(getXDimension(), getYDimension(), newMat);
    }

    @Override
    public Matrix clone() {
        return new Matrix(x, y, values);
    }

    private void setArray(double values[][]) {
        if (values.length != x) throw new IllegalArgumentException("Illegal values");
        if (values[0].length != y) throw new IllegalArgumentException("Illegal values");
        for (int i = 0; i < x; i++) for (int j = 0; j < y; j++) this.values[i][j] = values[i][j];
    }

    private void setArray(Double values[][]) {
        if (values.length != x) throw new IllegalArgumentException("Illegal values");
        if (values[0].length != y) throw new IllegalArgumentException("Illegal values");
        for (int i = 0; i < x; i++) for (int j = 0; j < y; j++) this.values[i][j] = values[i][j];
    }

}
