package com.rodrigofrazao.domain.imageTest;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.supportConstraints.Mask;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class ComplexImageTest {

    double[][] tesstArray, testAmplitude, testPhase;

    @Before
    public void setUp() {
        Random r = new Random();
        tesstArray = new double[15][30];
        testAmplitude = new double[10][11];
        testPhase = new double[10][11];
        for (int j = 0; j < testAmplitude.length; j++) {
            for (int k = 0; k < testAmplitude[0].length; k++) {
                testAmplitude[j][k] = (double) 256 * r.nextDouble();
                testPhase[j][k] =  -Math.PI + (Math.PI - (-Math.PI)) * r.nextDouble();
            }
        }

        for (int j = 0; j < tesstArray.length; j++) {
            for (int k = 0; k < tesstArray[0].length; k++) {
                tesstArray[j][k] = 1.0;
            }
        }
    }

    public void printArray(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void arrayTest() {
        printArray(tesstArray);
    }

    @Test
    public void createComplexImage(){
        ComplexImage complexImage = new ComplexImage(testAmplitude,testPhase);
        Assertions.assertThat(complexImage).isNotNull();
    }

    @Test
    public void addRandomPhaseTest(){
        ComplexImage complexImage = new ComplexImage(testAmplitude,testPhase);
        complexImage.addRandomPhase();
        Assertions.assertThat(testPhase).isNotEqualTo(complexImage.getPhase());
    }

    @Test
    public void realPartTest(){
        ComplexImage complexImage = new AmplitudeOnlyImage(testAmplitude);
        Assertions.assertThat(complexImage.re()).isNotNull();
    }

    @Test
    public void imaginaryPartTest(){
        ComplexImage complexImage = new ComplexImage(testAmplitude,testPhase);
        Assertions.assertThat(complexImage.im()).isNotNull();
    }

    @Test
    public void trueSupportContraintTest(){
        ComplexImage complexImage = new ComplexImage(testAmplitude,testPhase);
        Mask mask = new Mask(complexImage.width,complexImage.height);
        Assertions.assertThat(complexImage
                .withSupportConstraint(mask.ones())
                .compareAmplitude())
                .isEqualTo(complexImage
                        .compareAmplitude());
    }

    @Test
    public void paddingImageTest(){
        ComplexImage image = new AmplitudeOnlyImage(tesstArray);
        ComplexImage newImage=image.checkImageDimensions();
        Assertions.assertThat(newImage.getWidth()).isEqualTo(32);
        Assertions.assertThat(newImage.getHeight()).isEqualTo(16);
        System.out.println(newImage.amplitude[0][0]);
    }
}
