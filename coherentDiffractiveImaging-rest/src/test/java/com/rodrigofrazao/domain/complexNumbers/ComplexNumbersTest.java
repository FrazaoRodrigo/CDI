package com.rodrigofrazao.domain.complexNumbers;

import com.rodrigofrazao.domain.fourierTransform.Threaded_TwoD_FFT;
import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class ComplexNumbersTest {
    Complex complex;
    PolarComplex polar;
    double[][] testArray = new double[60][60];

    @Before
    public void setUp() {
        this.complex = new Complex(1.0D, 5.0D);
        this.polar = new PolarComplex(this.complex.abs(), this.complex.phase());

        for(int j = 0; j < 60; ++j) {
            for(int k = 0; k < 60; ++k) {
                testArray[j][k] =  Math.random() * 256;
            }
        }
    }

    @Test
    public void polarCreatiationTest() {
        Assert.assertEquals(1.0D, this.polar.re(), 1.0E-4D);
        Assert.assertEquals(5.0D, this.polar.im(), 1.0E-4D);

    }


}
