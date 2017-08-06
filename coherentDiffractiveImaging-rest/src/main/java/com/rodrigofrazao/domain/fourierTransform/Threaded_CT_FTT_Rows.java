package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;


import static com.rodrigofrazao.domain.complexNumbers.ComplexArray.getRows;
import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;



public class Threaded_CT_FTT_Rows implements Callable {
    int height,width,startIndx,nThreads;
    Complex[][] complexes;

    public Threaded_CT_FTT_Rows(int startIndx,int nThreads, Complex[][] complexes) {
        this.height = complexes.length;
        this.width =complexes[0].length;
        this.complexes = complexes;
        this.startIndx=startIndx;
        this.nThreads=nThreads;
    }

    @Override
    public HashMap<Integer,Complex[]> call() throws Exception {
        HashMap<Integer,Complex[]> hmap = new HashMap();
        for (int i = startIndx; i<height;i+=nThreads) {
            Complex[] fftrow = fft_ct(getRows(i,complexes));
            hmap.put(i,fftrow);
        }
        return hmap;
    }



}
