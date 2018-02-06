package io.github.ekardnam.sertraline.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Matrix extends AbstractMatrix {

    protected int rowDimension;

    protected int colDimension;

    protected double values[][];

    public Matrix(int rowDimension, int colDimension) {
        this.rowDimension = rowDimension;
        this.colDimension = colDimension;
        values = new double[rowDimension][colDimension];
        for (int i = 0; i < rowDimension; i++) for (int j = 0; j < colDimension; j++) values[i][j] = 0;
    }

    public Matrix(int rowDimension, int colDimension, double values[][]) {
       this(rowDimension, colDimension);
        setArray(values);
    }

    public Matrix(int rowDimension, int colDimension, AbstractVector rows[]) {
        //TODO("Better illegal messages")
        if (rows.length != colDimension) throw new IllegalArgumentException("Illegal");
        for (int i = 0; i < rows.length; i++) if (rows[i].getDimension() != rowDimension) throw new IllegalArgumentException("Illegal");
        this.rowDimension = rowDimension;
        this.colDimension = colDimension;
        values = new double[rowDimension][colDimension];
        for (int i = 0; i < rowDimension; i++) {
            for (int j = 0; j < colDimension; j++) {
                values[i][j] = rows[j].get(i);
            }
        }
    }

    @Override
    public int getRowDimension() { return rowDimension; }

    @Override
    public int getColDimension() { return colDimension; }

    @Override
    public AbstractMatrix add(AbstractMatrix other) {
        double newMat[][] = new double[getRowDimension()][getColDimension()];
        for (int i = 0; i < getRowDimension(); i++) for (int j = 0; j < getColDimension(); j++) newMat[i][j] = get(i, j) + other.get(i, j);
        return new Matrix(getRowDimension(), getColDimension(), newMat);
    }

    @Override
    public AbstractMatrix multiply(double scalar) {
        double newMat[][] = new double[getRowDimension()][getColDimension()];
        for (int i = 0; i < getRowDimension(); i++) for (int j = 0; j < getColDimension(); j++) newMat[i][j] = scalar * get(i, j);
        return new Matrix(getRowDimension(), getColDimension(), newMat);
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
        if (vector.getDimension() != rowDimension) throw new IllegalArgumentException("Illegal");
        List<Double> newVec = new ArrayList<>();
        for (AbstractVector row : rows()) {
            newVec.add(row.dot(vector));
        }
        return new Vector(howManyRows(), newVec.stream().mapToDouble(value -> value).toArray());
    }

    @Override
    public AbstractMatrix multiply(AbstractMatrix other) {
        if (getRowDimension() != other.getColDimension()) throw new IllegalArgumentException("Illegal");
        double newMat[][] = new double[howManyRows()][other.howManyCols()];
        for (int i = 0; i < howManyRows(); i++) {
            for (int j = 0; j < other.howManyCols(); j++) {
                newMat[i][j] = getRow(i).dot(other.getCol(j));
            }
        }
        return new Matrix(howManyRows(), other.howManyCols(), newMat);
    }

    @Override
    public AbstractMatrix multiplyOneToOne(AbstractMatrix other) {
        if (getRowDimension() != other.getRowDimension() && getColDimension() != other.getColDimension()) throw new IllegalArgumentException("Illegal");
        double newMat[][] = new double[getRowDimension()][getColDimension()];
        for (int i = 0; i < getRowDimension(); i++) for (int j = 0; j < getColDimension(); j++) newMat[i][j] = get(i, j) * other.get(i, j);
        return new Matrix(getRowDimension(), getColDimension(), newMat);
    }

    @Override
    public double get(int row, int col) {
        return values[row][col];
    }

    @Override
    public AbstractMatrix transpose() {
        double newMat[][] = new double[getColDimension()][getRowDimension()];
        for (int i = 0; i < getRowDimension(); i++) for (int j = 0; j < getColDimension(); j++) newMat[j][i] = get(i, j);
        return new Matrix(getColDimension(), getRowDimension(), newMat);
    }

    @Override
    public AbstractMatrix copy() {
        return new Matrix(rowDimension, colDimension, values);
    }

    @Override
    public Iterable<AbstractVector> rows() {
        return new Iterable<AbstractVector>() {
            @Override
            public Iterator<AbstractVector> iterator() {
                return new Iterator<AbstractVector>() {
                    private int index = 0;

                    @Override
                    public boolean hasNext() {
                        return index < getColDimension();
                    }

                    @Override
                    public AbstractVector next() {
                        AbstractVector vector = getRow(index);
                        index++;
                        return vector;
                    }
                };
            }
        };
    }

    @Override
    public AbstractVector getRow(int index) {
        double vector[] = new double[getRowDimension()];
        for (int i = 0; i < getRowDimension(); i++) {
            vector[i] = get(i, index);
        }
        index++;
        return new Vector(getRowDimension(), vector);
    }

    @Override
    public AbstractVector getCol(int index) {
        return transpose().getRow(index);
    }

    @Override
    public Iterable<AbstractVector> cols() {
        return transpose().rows();
    }

    @Override
    public AbstractMatrix map(Function<Double, Double> map) {
        double newMat[][] = new double[getRowDimension()][getColDimension()];
        for (int i = 0; i < getRowDimension(); i++) {
            for (int j = 0; j < getColDimension(); j++) {
                newMat[i][j] = map.apply(get(i, j));
            }
        }
        return new Matrix(getRowDimension(), getColDimension(), newMat);
    }

    private void setArray(double values[][]) {
        if (values.length != rowDimension) throw new IllegalArgumentException("Illegal values");
        if (values[0].length != colDimension) throw new IllegalArgumentException("Illegal values");
        for (int i = 0; i < rowDimension; i++) for (int j = 0; j < colDimension; j++) this.values[i][j] = values[i][j];
    }

    private void setArray(Double values[][]) {
        if (values.length != rowDimension) throw new IllegalArgumentException("Illegal values");
        if (values[0].length != colDimension) throw new IllegalArgumentException("Illegal values");
        for (int i = 0; i < rowDimension; i++) for (int j = 0; j < colDimension; j++) this.values[i][j] = values[i][j];
    }

}
