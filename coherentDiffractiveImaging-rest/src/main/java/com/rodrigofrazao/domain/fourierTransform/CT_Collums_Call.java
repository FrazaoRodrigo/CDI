package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;

import java.util.Arrays;
import java.util.concurrent.Callable;

import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;

public class CT_Collums_Call implements Callable {

    int nThreads,nbrOfCollums,startIndx;
    Complex[] inputInlineComplex;
    int height = inputInlineComplex.length/nbrOfCollums;

    public CT_Collums_Call(int nThreads, int nbrOfCollums, int startIndx, Complex[] inputInlineComplex) {
        this.nThreads = nThreads;
        this.nbrOfCollums = nbrOfCollums;
        this.startIndx = startIndx;
        this.inputInlineComplex = inputInlineComplex;
    }

    @Override
    public Complex[] call() throws Exception {
        Complex[] collumsFFT = new Complex[height];
        for(int j = startIndx; j< nbrOfCollums; j+=nThreads) {
            collumsFFT =fft_ct(Arrays.copyOfRange(inputInlineComplex, j, j + height - 1));
        }
        return collumsFFT;
    }
}
