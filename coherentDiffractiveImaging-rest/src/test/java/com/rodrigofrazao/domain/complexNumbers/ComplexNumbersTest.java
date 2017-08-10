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

import static com.rodrigofrazao.domain.complexNumbers.Complex.reArray;

public class ComplexNumbersTest {
    Complex complex;
    PolarComplex polar;
    Complex[] testArray = new Complex[10];

    @Before
    public void setUp() {
        this.complex = new Complex(1.0D, 5.0D);
        this.polar = new PolarComplex(this.complex.abs(), this.complex.phase());

        for(int j = 0; j < 10; ++j) {

                testArray[j] = new Complex( Math.random() * 256,Math.random() * 256);

        }
    }

    @Test
    public void polarCreatiationTest() {
        Assert.assertEquals(1.0D, this.polar.re(), 1.0E-4D);
        Assert.assertEquals(5.0D, this.polar.im(), 1.0E-4D);

    }

    @Test
    public void reArrayTest(){
      double[] real =  reArray(testArray);
    }

    @Test
    public void smth(){
        System.out.println(5/2);
    }

}
