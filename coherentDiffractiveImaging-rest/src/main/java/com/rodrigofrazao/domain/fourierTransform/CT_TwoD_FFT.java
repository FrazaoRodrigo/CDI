package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.PolarComplex;
import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.InlineComplexImage;

import java.util.ArrayList;
import java.util.List;

import static com.rodrigofrazao.domain.complexNumbers.ComplexArray.buildFromRowsList;
import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;

public class CT_TwoD_FFT {

    public static ComplexImage twoD_fft_ct(ComplexImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        List<Complex[]> temp = new ArrayList<>();
        InlineComplexImage inlineComplexImage = image.toInlineImage();
        for (int i = 0; i<height;i++) {
            Complex[] fftrows = fft_ct(inlineComplexImage.getRow(i));
            temp.add(fftrows);
        }
        Complex[][] horizontalFFT = temp.toArray(new Complex[height][width]);
        temp.clear();
        for (int i = 0; i<width;i++){
            Complex[] collum = getCollums(i,horizontalFFT,height);
            Complex[] fftcollum = fft_ct(collum);
            temp.add(fftcollum);
        }
        buildFromCollumsList(temp);
    }

    private static Complex[] getCollums(int index,Complex[][] matrix,int height){
        Complex[] result = new Complex[height];
        for(int row = 0; row < height; row++)
        {
            result[row] = matrix[row][index];
        }
        return result;
    }


}
