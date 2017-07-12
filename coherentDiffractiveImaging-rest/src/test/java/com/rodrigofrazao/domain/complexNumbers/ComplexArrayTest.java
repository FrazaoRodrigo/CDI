package com.rodrigofrazao.domain.complexNumbers;

import org.junit.Before;
import org.junit.Test;

import static com.rodrigofrazao.domain.complexNumbers.ComplexArray.getCollumAsComplex;
import static com.rodrigofrazao.domain.complexNumbers.ComplexArray.getRowAsComplex;

public class ComplexArrayTest {

   private Complex[][] testArray= new Complex[10][10];

    @Before
    public void setUp() {

        for(int j = 0; j < 10; ++j) {
            for(int k = 0; k < 10; ++k) {
                testArray[j][k] = new Complex( Math.random() * 256,0);
            }
        }
    }

    @Test
    public void getRowTest(){
        Complex[] row = getRowAsComplex(testArray,5);
        printArray(row);
    }

    @Test
    public void getCollumTest(){
        Complex[] row = getCollumAsComplex(testArray,5);
        printArray(row);
    }

    public void printArray(Complex[] array) {
        for (int i = 0; i < array.length; i++) {

                System.out.print(array[i].re() + " ");

        }
    }
}
