package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;

import java.util.HashMap;
import java.util.concurrent.Callable;

import static com.rodrigofrazao.domain.complexNumbers.ComplexArray.getCollums;
import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;


public class Threaded_CT_FFT_Collums implements Callable {
    int height, width, startIndx, nThreads;
    Complex[][] complexes;

    public Threaded_CT_FFT_Collums(int startIndx, int nThreads, Complex[][] complexes) {
        this.height = complexes.length;
        this.width = complexes[0].length;
        this.startIndx = startIndx;
        this.nThreads = nThreads;
        this.complexes = complexes;
    }

    @Override
    public HashMap<Integer,Complex[]> call() throws Exception {
        HashMap<Integer,Complex[]> hmap = new HashMap();
        for (int i = startIndx; i < width; i += nThreads) {
          Complex[] fftCollums = fft_ct(getCollums(i, complexes));
          hmap.put(i,fftCollums);
        }
        return hmap;
    }
}

