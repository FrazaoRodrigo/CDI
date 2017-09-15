package com.rodrigofrazao.domain.fourierTransform;

import static com.rodrigofrazao.domain.fourierTransform.Fftshift.shiftOrigin;

public class Ifftshift {

    public static double[][] iShiftOrigin(double[][] input) {
        double [][] res= transposeMatrix(shiftOrigin(input));
        return transposeMatrix(shiftOrigin(res));
    }

    public static double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }
}
