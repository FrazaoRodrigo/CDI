package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;

import java.util.concurrent.Callable;

import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;
import static com.rodrigofrazao.domain.fourierTransform.CT_TwoD_FFT.getRows;

public class Threaded_CT_FFT_Collums implements Callable {
    int height, width, startIndx, nThreads;
    Complex[][] complexes;

    public Threaded_CT_FFT_Collums(int height, int width, int startIndx, int nThreads, Complex[][] complexes) {
        this.height = height;
        this.width = width;
        this.startIndx = startIndx;
        this.nThreads = nThreads;
        this.complexes = complexes;
    }

    @Override
    public Complex[][] call() throws Exception {
        Complex[][] fftCollums = new Complex[width][height];
        for (int i = startIndx; i < width; i += nThreads) {
            fftCollums[i] = fft_ct(getRows(i, complexes, height));
        }
        return fftCollums;
    }
}

