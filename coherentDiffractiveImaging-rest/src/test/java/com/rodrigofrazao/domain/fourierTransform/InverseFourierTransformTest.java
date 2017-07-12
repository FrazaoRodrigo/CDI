package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ReadImage;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.ExecutionException;

import static com.rodrigofrazao.domain.fourierTransform.InverseTwoD_FFT.inverseXform2D;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.inverseTwoDFFT_thread;

public class InverseFourierTransformTest {


    double[][] testArray = new double[60][60];
    double[][] testArrayPhase = new double[60][60];
    ComplexImage image;

    @Before
    public void setUp() {

        for(int j = 0; j < 60; ++j) {
            for(int k = 0; k < 60; ++k) {
                testArray[j][k] =  Math.random() * 256;
                testArrayPhase[j][k] =  Math.random() * 256;
            }
        }
        image = new ComplexImage(testArray,testArrayPhase);
    }

    @Test
    public void inverseFFTTest() throws ExecutionException, InterruptedException {
        ComplexImage invFourierImage = inverseXform2D(image.re(),image.im());
        ComplexImage invThreadedFourierImage = inverseTwoDFFT_thread(image,10);
        Assertions.assertThat(invFourierImage).isEqualTo(invThreadedFourierImage);
    }

    @Test
    public void performancetest() throws ExecutionException, InterruptedException {
        ComplexImage invThreadedFourierImage = inverseTwoDFFT_thread(image,10);
    }
}
