package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.ComplexImage;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class FourierShiftTest {

    ComplexImage image;
    double[][] testArray = new double[8][4];

    @Before
    public void setUp(){
        for(int j = 0; j < 8; ++j) {
            for(int k = 0; k < 4; ++k) {
                testArray[j][k] =  Math.random() * 256;
            }
        }
        image= new ComplexImage(testArray,testArray);
    }

    @Test
    public void shouldPreformAFullShift(){
        Assertions.assertThat(image.shiftWithPhase().iShiftWithPhase()).isEqualTo(image);
    }


    @Test
    public void fullFourierCicle() throws ExecutionException, InterruptedException {

        Assertions.assertThat(image.fft()
                .invfft()
                .compareAmplitude())
                .isEqualTo(image
                        .compareAmplitude());
    }


    @Test
    public void shouldPreformAFullShiftwithfft() throws ExecutionException, InterruptedException {
       Assertions.assertThat(image.fft()
               .shiftWithPhase()
               .iShiftWithPhase()
               .invfft()
               .compareAmplitude())
               .isEqualTo(image
                       .compareAmplitude());
    }

}
