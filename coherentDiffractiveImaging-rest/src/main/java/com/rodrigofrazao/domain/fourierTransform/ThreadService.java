package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;

import java.util.*;
import java.util.concurrent.*;

import static com.rodrigofrazao.domain.complexNumbers.ComplexArray.arraySum;
import static com.rodrigofrazao.domain.complexNumbers.ComplexArray.zerosComplexArray;

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


    public static Complex[][] fft_CT_rows(int nThreads,Complex[][] input) throws ExecutionException, InterruptedException {

        int height = input.length;
        int width =input[0].length;

        List<Future<Complex[][]>> resultArray = new ArrayList<>();
        Complex[][] outArray = zerosComplexArray(height,width);
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new Threaded_CT_FTT_Rows(height, width, i, nThreads, input);
            Future<Complex[][]> result = executor.submit(fftThread);
            resultArray.add(i, result);
        }
        executor.shutdown();
        for (Future<Complex[][]> future : resultArray) {
            outArray = arraySum(outArray, future.get());
        }
            return outArray;
    }

    public static Complex[][] fft_CT_collums(int nThreads,Complex[][] input,int height,int width) throws ExecutionException, InterruptedException {

        List<Future<Complex[][]>> resultArray=new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        Complex[][] outArray = zerosComplexArray(width,height);
        for (int i = 0; i < nThreads; i++) {
            Callable fftThread = new Threaded_CT_FFT_Collums(height,width,i,nThreads,input);
            Future<Complex[][]> result = executor.submit(fftThread);
            resultArray.add(i,result);
        }
        executor.shutdown();
        for (Future<Complex[][]> future : resultArray){
            outArray = arraySum(outArray,future.get());
        }
        return outArray;
    }
}
