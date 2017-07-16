package com.rodrigofrazao.domain.complexNumbers;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.InlineComplexImage;

import java.util.ArrayList;

public class ComplexArray {


    public static Complex[][] arraySum(Complex[][] one, Complex[][] two) {
        Complex[][] result = new Complex[one.length][one[0].length];
        for (int i = 0; i < one.length; i++) {
            for (int j = 0; j < one[0].length; j++) {
                if (two[i][j] == null) {two[i][j] = new Complex(0.0,0.0);}
                result[i][j] = one[i][j].plus(two[i][j]);
            }
        }
        return result;
    }

    public static Complex[][] zerosComplexArray(int height, int width){
        Complex[][] result = new Complex[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = new Complex(0.0,0.0);
            }
        }
        return result;
    }
}
