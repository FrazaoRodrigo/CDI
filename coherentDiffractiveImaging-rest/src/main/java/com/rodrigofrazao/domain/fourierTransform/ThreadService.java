package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.SparseMatrix;
import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;

import java.util.*;
import java.util.concurrent.*;


public class ThreadService {

    public static ComplexImage twoDFFT_thread(double[][] input, int nThreads) throws ExecutionException, InterruptedException {
        int height = input.length;
        int width = input[0].length;
        ComplexImage fourierImage = new AmplitudeOnlyImage(new double[height][width]);
        List<Future<ComplexImage>> resultArray=new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new Threaded_TwoD_FFT(i,nThreads,input);
            Future<ComplexImage> result = executor.submit(fftThread);
            resultArray.add(result);
        }
        executor.shutdown();
        for (Future<ComplexImage> future : resultArray){
            ComplexImage image = future.get();
            fourierImage.sumComplex(image);
        }
        return fourierImage;
    }


    public static ComplexImage twoDFFT_inline_thread(ComplexImage input, int nThreads) throws ExecutionException, InterruptedException {
        int height = input.getHeight();
        int width = input.getWidth();
        ComplexImage fourierImage = new AmplitudeOnlyImage(new double[height][width]);
        List<Future<ComplexImage>> resultArray=new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new Threaded_Inline_TwoD_FFT(i,nThreads,input.toInlineImage());
            Future<ComplexImage> result = executor.submit(fftThread);
            resultArray.add(result);
        }
        executor.shutdown();
        for (Future<ComplexImage> future : resultArray){
            ComplexImage image = future.get();
            fourierImage.sumComplex(image);
        }
        return fourierImage;
    }


    public static ComplexImage inverseTwoDFFT_thread(ComplexImage input, int nThreads) throws ExecutionException, InterruptedException {
        int height = input.getAmplitude().length;
        int width = input.getAmplitude()[0].length;
        ComplexImage directImage = new AmplitudeOnlyImage(new double[height][width]);
        List<Future<ComplexImage>> resultArray=new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new Threaded_Inverse_TwoD_FFT(input,i,nThreads);
            Future<ComplexImage> result = executor.submit(fftThread);
            resultArray.add(result);
        }
        executor.shutdown();
        for (Future<ComplexImage> future : resultArray){
            ComplexImage image = future.get();
            directImage.sumComplex(image);
        }
        return directImage;
    }


    public static SparseMatrix rowsCT(int nThreads, SparseMatrix input) throws ExecutionException, InterruptedException {

        List<Future<SparseMatrix>> resultArray = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        SparseMatrix outArray = new SparseMatrix(input.getHeight(),input.getWidth());

        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new CT_Rows_Call(i,nThreads,input);
            Future<SparseMatrix> result = executor.submit(fftThread);
            resultArray.add(result);
        }
        for(Future<SparseMatrix> future : resultArray){
           outArray.addSparseMatrix(future.get());
        }
        executor.shutdown();

        return outArray;
    }

    public static SparseMatrix collumsCT(int nThreads, SparseMatrix input) throws ExecutionException, InterruptedException {

        List<Future<SparseMatrix>> resultArray = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        SparseMatrix outArray = new SparseMatrix(input.getHeight(),input.getWidth());

        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new CT_Collums_Call(i, nThreads, input);
            Future<SparseMatrix> result = executor.submit(fftThread);
            resultArray.add(result);
        }
        for(Future<SparseMatrix> future : resultArray){
            outArray.addSparseMatrix(future.get());
        }
        executor.shutdown();

        return outArray;
    }
}
