package com.rodrigofrazao.domain.fourierTransform;


import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;


import java.util.ArrayList;
import java.util.List;
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
}
