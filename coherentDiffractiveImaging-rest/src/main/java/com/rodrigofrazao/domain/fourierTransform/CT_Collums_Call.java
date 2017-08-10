package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.SparseMatrix;
import java.util.concurrent.Callable;

import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;
import static com.rodrigofrazao.domain.fourierTransform.CT_1D_Inverse_FFT.ifft_ct;

public class CT_Collums_Call implements Callable {

    int nThreads,nbrOfCollums,startIndx;
    SparseMatrix sparseMatrix;
    String option;


    public CT_Collums_Call( int startIndx,int nThreads,  SparseMatrix sparseMatrix, String option) {
        this.nThreads = nThreads;
        this.nbrOfCollums = sparseMatrix.getWidth();
        this.startIndx = startIndx;
        this.sparseMatrix = sparseMatrix;
        this.option=option;
    }

    @Override
    public SparseMatrix call() throws Exception {
        SparseMatrix outCollum = new SparseMatrix(sparseMatrix.getHeight(),sparseMatrix.getWidth());
        for(int j = startIndx; j< nbrOfCollums; j+=nThreads) {
            switch (option) {
                case "direct":
                    outCollum.setCollum(j, fft_ct(sparseMatrix.getCollum(j)));
                    break;
                case "inverse":
                    outCollum.setCollum(j, ifft_ct(sparseMatrix.getCollum(j)));
            }
        }
        return outCollum;
    }
}

