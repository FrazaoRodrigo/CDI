package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.SparseMatrix;

import java.util.concurrent.Callable;

import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;

public class CT_Collums_Call implements Callable {

    int nThreads,nbrOfCollums,startIndx;
    SparseMatrix sparseMatrix;


    public CT_Collums_Call( int startIndx,int nThreads,  SparseMatrix sparseMatrix) {
        this.nThreads = nThreads;
        this.nbrOfCollums = sparseMatrix.getWidth();
        this.startIndx = startIndx;
        this.sparseMatrix = sparseMatrix;

    }

    @Override
    public SparseMatrix call() throws Exception {
        SparseMatrix outCollum = new SparseMatrix(sparseMatrix.getHeight(),sparseMatrix.getWidth());
        for(int j = startIndx; j< nbrOfCollums; j+=nThreads) {
            outCollum.setCollum(j,fft_ct(sparseMatrix.getCollum(j)));
        }
        return outCollum;
    }
}

