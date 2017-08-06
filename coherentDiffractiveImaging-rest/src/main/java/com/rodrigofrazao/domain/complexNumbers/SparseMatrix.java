package com.rodrigofrazao.domain.complexNumbers;

import java.util.HashMap;

public class SparseMatrix {


    private int rows;
    private int columns;
    private HashMap<Index,Complex> values;


    public SparseMatrix(Complex[][] contents) {
        this.rows=contents.length;
        this.columns=contents[0].length;
        for (int i=0; i<contents.length; i++)
            for (int j=0; j<contents[0].length; j++)
                setElement(i,j,contents[i][j]);
    }

    public Complex[] getRow(int index) {
        Complex[] outArray = new Complex[columns];
        for (int i = 0; i<columns;i++){
            outArray[i]= getElement(index,i);
        }
        return outArray;
    }

    public Complex[] getCollum(int index) {
        Complex[] outArray = new Complex[rows];
        for (int i = 0; i<rows;i++){
            outArray[i]= getElement(i,index);
        }
        return outArray;
    }

    public Complex[] setRow(int index,Complex[] row) {
        Complex[] outArray = new Complex[columns];
        for (int i = 0; i<columns;i++){
            setElement(index,i,row[i]);
        }
        return outArray;
    }

    public Complex[] setCollum(int index,Complex[] collum) {
        Complex[] outArray = new Complex[rows];
        for (int i = 0; i<rows;i++){
            setElement(i,index,collum[i]);
        }
        return outArray;
    }


    public int getNumberOfRows() {
        return rows;
    }

    public int getNumberOfColumns() {
        return columns;
    }

    public Complex getElement(int row, int column) {
        Index index = new Index (row,column);
       return values.get(index);
    }

    public void setElement(int row, int column, Complex value)  {

        Index index = new Index(row,column);
        this.values.put(index,value);
    }


}

class Index{

    private int x=0; // nr of rows
    private int y=0; // nr of columns


    public Index (final int x, final int y)
    {
        this.x=x;
        this.y=y;
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

