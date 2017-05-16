package com.rodrigofrazao.domain.complexNumbers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComplexNumbersTest {
    Complex complex;
    PolarComplex polar;



    @Before
    public void setUp() {
        this.complex = new Complex(1.0D, 5.0D);
        this.polar = new PolarComplex(this.complex.abs(), this.complex.phase());
    }

    @Test
    public void polarCreatiationTest() {
        Assert.assertEquals(1.0D, this.polar.re(), 1.0E-4D);
        Assert.assertEquals(5.0D, this.polar.im(), 1.0E-4D);
    }
}
