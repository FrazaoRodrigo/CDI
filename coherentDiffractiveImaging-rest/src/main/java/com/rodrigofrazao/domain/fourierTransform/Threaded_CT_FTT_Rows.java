package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;

import java.util.concurrent.Callable;


import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;
import static com.rodrigofrazao.domain.fourierTransform.CT_TwoD_FFT.getRows;


public class Threaded_CT_FTT_Rows implements Callable {
    int height,width,startIndx,nThreads;
    Complex[][] complexes;

    public Threaded_CT_FTT_Rows(int height, int width, int startIndx,int nThreads, Complex[][] complexes) {
        this.height = height;
        this.width = width;
        this.complexes = complexes;
        this.startIndx=startIndx;
        this.nThreads=nThreads;
    }

    @Override
    public Complex[][] call() throws Exception {
        Complex[][] fftrow = new Complex[height][width];
        for (int i = startIndx; i<height;i+=nThreads) {
            fftrow[i] = fft_ct(getRows(i,complexes,width));
        }
        return fftrow;
    }



}
