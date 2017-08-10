package com.rodrigofrazao.domain.fourierTransform;


import com.rodrigofrazao.domain.complexNumbers.SparseMatrix;
import java.util.concurrent.Callable;


import static com.rodrigofrazao.domain.fourierTransform.CT_1D_FFT.fft_ct;
import static com.rodrigofrazao.domain.fourierTransform.CT_1D_Inverse_FFT.ifft_ct;

public class CT_Rows_Call implements Callable {

    int startIndx,nbrOfRows,nThreads;
    SparseMatrix sparseMatrix;
    String option;

    public CT_Rows_Call(int startIndx, int nThreads, SparseMatrix sparseMatrix, String option) {
        this.startIndx = startIndx;
        this.nbrOfRows = sparseMatrix.getHeight();
        this.nThreads = nThreads;
        this.sparseMatrix = sparseMatrix;
        this.option = option;
    }

    @Override
    public SparseMatrix call() throws Exception {
        SparseMatrix outRows = new SparseMatrix(sparseMatrix.getHeight(),sparseMatrix.getWidth());
            for(int j = startIndx; j< nbrOfRows; j+=nThreads) {
              switch (option) {
                  case "direct":
                      outRows.setRow(j, fft_ct(sparseMatrix.getRow(j)));
                      break;
                  case "inverse":
                      outRows.setRow(j, ifft_ct(sparseMatrix.getRow(j)));
                      break;
              }
            }
    return outRows;
    }
}
