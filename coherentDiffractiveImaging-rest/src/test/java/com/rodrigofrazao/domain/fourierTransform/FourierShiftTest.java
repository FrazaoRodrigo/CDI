package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.ComplexImage;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class FourierShiftTest {

    ComplexImage image = new ComplexImage();
    double[][] testArray = new double[8][4];

    @Before
    public void setUp(){
        for(int j = 0; j < 8; ++j) {
            for(int k = 0; k < 4; ++k) {
                testArray[j][k] =  Math.random() * 256;
            }
        }
        image.setAmplitude(testArray);
        image.phase=testArray;
    }

    @Test
    public void shouldPreformAFullShift(){
        Assertions.assertThat(image.shiftWithPhase().iShiftWithPhase()).isEqualTo(image);
    }

}
