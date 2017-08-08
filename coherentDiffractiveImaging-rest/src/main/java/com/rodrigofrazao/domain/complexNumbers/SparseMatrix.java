package com.rodrigofrazao.domain.complexNumbers;

import com.rodrigofrazao.domain.image.ComplexImage;

import java.util.HashMap;

public class SparseMatrix {

    private int height;
    private int width;
    private HashMap<Index, Complex> values = new HashMap<>();

    public SparseMatrix(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public SparseMatrix(ComplexImage image) {
        this.height = image.getHeight();
        this.width = image.getWidth();
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                Index index = new Index(j, k);
                values.put(index, image.toComplex(j, k));
            }
        }
    }

    public Complex[] getRow(int index) {
        Complex[] outArray = new Complex[width];
        for (int i = 0; i < width; i++) {
            outArray[i] = getElement(index, i);
        }
        return outArray;
    }

    public Complex[] getCollum(int index) {
        Complex[] outArray = new Complex[height];
        for (int i = 0; i < height; i++) {
            outArray[i] = getElement(i, index);
        }
        return outArray;
    }

    public void setRow(int index, Complex[] row) {

        for (int i = 0; i < width; i++) {
            setElement(index, i, row[i]);
        }
    }

    public void setCollum(int index, Complex[] collum) {

        for (int i = 0; i < height; i++) {
            setElement(i, index, collum[i]);
        }
    }

    public void addSparseMatrix(SparseMatrix sparseMatrix){
        values.putAll(sparseMatrix.getMap());
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public HashMap<Index,Complex> getMap(){return values;}

    public Complex getElement(int height, int width) {
        Index index = new Index(height,width);
        return values.get(index);
    }

    public void setElement( int height,int width, Complex value) {

        Index index = new Index( height,width);
        this.values.put(index, value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SparseMatrix that = (SparseMatrix) o;

        if (height != that.height) return false;
        if (width != that.width) return false;
        return values != null ? values.equals(that.values) : that.values == null;
    }

    @Override
    public int hashCode() {
        int result = height;
        result = 31 * result + width;
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }
}

class Index {

    private int x; // nr of height
    private int y; // nr of width


    public Index(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Index index = (Index) o;

        if (x != index.x) return false;
        return y == index.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

