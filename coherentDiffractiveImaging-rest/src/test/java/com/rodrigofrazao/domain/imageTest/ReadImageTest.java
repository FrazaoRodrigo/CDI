package com.rodrigofrazao.domain.imageTest;

import com.rodrigofrazao.domain.image.ReadImage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ReadImageTest {
    ReadImage image;

    public ReadImageTest() {
    }

    @Before
    public void setUp() {
        File f = new File("C:\\Users\\rodrpmff\\CDI\\images\\Capture.PNG");
        this.image = new ReadImage(f);
    }

    @Test
    public void readExistingImageNotNull() {
        Assert.assertNotNull(this.image);
    }

    @Test
    public void saveToTest() {
        try {
            ReadImage var10000 = this.image;
            ReadImage.saveToFile(this.image.toSave(), "C:\\Users\\rodrpmff\\CDI\\images\\Capturetest.PNG");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}
