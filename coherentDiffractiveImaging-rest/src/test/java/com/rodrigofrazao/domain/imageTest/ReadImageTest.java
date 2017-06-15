package com.rodrigofrazao.domain.imageTest;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ReadImage;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ReadImageTest {
    ReadImage image;

    @Before
    public void setUp() {
        File f = new File("C:\\Users\\rodrpmff\\CDI\\images\\example.bmp");
        this.image = new ReadImage(f);
    }

    @Test
    public void readExistingImageNotNull() {
        Assert.assertNotNull(this.image);
    }

    @Test
    public void saveToTest() {
        try {
            ReadImage.saveToFile(image.toSave(), "C:\\Users\\rodrpmff\\CDI\\images\\Capturetest.PNG");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    @Test
    public void buffuredImageToArrayTest(){
        double[][] imageArray = image.bufferedImageToArray();
        Assertions.assertThat(imageArray).doesNotContainNull();
    }

    @Test
    public void buffuredImageToComplexImageTest(){
        ComplexImage complexImage = image.bufferedImageToComplexImage();
        Assertions.assertThat(complexImage.re()).doesNotContainNull();
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
