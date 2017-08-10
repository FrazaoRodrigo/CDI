package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ReadImage;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.ExecutionException;


import static com.rodrigofrazao.domain.fourierTransform.TwoD_FFT.twoDfft;

public class FourierTransformTest {

    ComplexImage complexImage;
    double[][] testArray = new double[64][32];

    @Before
    public void setUp() {
        File f = new File("C:\\Users\\rodrpmff\\CDI\\images\\example.bmp");
        ReadImage image = new ReadImage(f);
        complexImage = image.bufferedImageToComplexImage();

        for(int j = 0; j < 64; ++j) {
            for(int k = 0; k < 32; ++k) {
                testArray[j][k] =  Math.random() * 256;
            }
        }
    }

    @Test
    public void fourierTransformTest() throws ExecutionException, InterruptedException {
       ComplexImage fourierTransformedImage =  twoDfft(testArray);
        ComplexImage test = new AmplitudeOnlyImage(testArray);
        ComplexImage fourier = test.fft_thread();
        Assertions.assertThat(fourier.getAmplitude()).isEqualTo(fourierTransformedImage.getAmplitude());
    }


    @Test
    public void fftTest() throws InterruptedException, ExecutionException {
        ComplexImage test = new AmplitudeOnlyImage(testArray);
        ComplexImage fourier = test.fft_thread();
        ComplexImage fourierInLine = test.fft_inline_thread();
        Assertions.assertThat(fourier.getAmplitude()).isEqualTo(fourierInLine.getAmplitude());
    }

    @Test
    public void performance() throws InterruptedException, ExecutionException {
        ComplexImage test = new AmplitudeOnlyImage(testArray);
        ComplexImage fourierInLine = test.fft_CP_thread_2V();
    }





    public void printArray(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
