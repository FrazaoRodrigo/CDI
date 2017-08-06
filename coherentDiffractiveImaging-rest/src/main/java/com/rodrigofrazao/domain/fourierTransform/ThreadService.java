package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.SparseMatrix;
import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;
import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;

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


    public static HashMap<Integer,Complex[]> fft_CT_rows(int nThreads,Complex[][] input) throws ExecutionException, InterruptedException {

        List<Future<HashMap<Integer,Complex[]>>> out = new ArrayList<>();
        HashMap<Integer,Complex[]> hmap = new HashMap();

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new Threaded_CT_FTT_Rows(i, nThreads, input);
            Future<HashMap<Integer,Complex[]>> result = executor.submit(fftThread);
            out.add(result);
        }
        for(Future<HashMap<Integer,Complex[]>> future : out){
            hmap.putAll(future.get());
        }
        executor.shutdown();
        return hmap;
    }

    public static HashMap<Integer,Complex[]> fft_CT_collums(int nThreads,Complex[][] input) throws ExecutionException, InterruptedException {

        List<Future<HashMap<Integer,Complex[]>>> out = new ArrayList<>();
        HashMap<Integer,Complex[]> hmap = new HashMap();

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new Threaded_CT_FFT_Collums(i,nThreads,input);
            Future<HashMap<Integer,Complex[]>> result = executor.submit(fftThread);
            out.add(result);
        }
        for(Future<HashMap<Integer,Complex[]>> future : out){
            hmap.putAll(future.get());
        }
        executor.shutdown();
        return hmap;
    }

    public static Complex[] rowsCT(int nThreads, SparseMatrix input) throws ExecutionException, InterruptedException {

        List<Future<Complex[]>> resultArray=new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        Complex[] outArray = new Complex[input.length];
        int width = input.length/nbrOfRows;

        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new CT_Rows_Call(i,nThreads,input);
            Future<Complex[]> result = executor.submit(fftThread);
            resultArray.add(result);
        }
        for(Future<Complex[]> future : resultArray){
            for (int i = 0; i< nbrOfRows; i++) {
                Arrays.fill(outArray, i*width, (i+1)*width - 1, future.get());
            }
        }
        executor.shutdown();

        return outArray;
    }

    public static Complex[] collumsCT(int nThreads, int nbrOfCollums, Complex[] input) throws ExecutionException, InterruptedException {
        List<Future<Complex[]>> resultArray=new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        Complex[] outArray = new Complex[input.length];
        int height = input.length/nbrOfCollums;

        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new CT_Collums_Call(nThreads,nbrOfCollums,i, input);
            Future<Complex[]> result = executor.submit(fftThread);
            resultArray.add(result);
        }
        for(Future<Complex[]> future : resultArray){
            for (int i = 0; i< nbrOfCollums; i++) {
                Arrays.fill(outArray, i*height, (i+1)*height - 1, future.get());
            }
        }
        executor.shutdown();

        return outArray;
    }
}
