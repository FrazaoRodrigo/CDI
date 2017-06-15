package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ReadImage;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static com.rodrigofrazao.domain.fourierTransform.TwoD_FFT.twoDfft;

public class FourierTransformTest {

    ComplexImage complexImage;

    @Before
    public void setUp() {
        File f = new File("C:\\Users\\rodrpmff\\CDI\\images\\example.bmp");
        ReadImage image = new ReadImage(f);
        complexImage = image.bufferedImageToComplexImage();
    }

    @Test
    public void fourierTransformTest(){
       ComplexImage fourierTransformedImage =  twoDfft(complexImage.getAmplitude());
       printArray(fourierTransformedImage.getAmplitude());
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
