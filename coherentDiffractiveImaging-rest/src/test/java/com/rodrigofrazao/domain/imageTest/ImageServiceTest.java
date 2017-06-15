package com.rodrigofrazao.domain.imageTest;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import com.rodrigofrazao.domain.image.ReadImage;
import com.rodrigofrazao.domain.supportConstraints.Mask;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageServiceTest {

    ComplexImage complexImage;
    ReadImage image;
    ImageService imageService = new ImageService();

    @Before
    public void setUp() {
        File f = new File("C:\\Users\\rodrpmff\\CDI\\images\\example.bmp");
        image = new ReadImage(f);
        complexImage = image.bufferedImageToComplexImage();
    }

    @Test
    public void complexImageToBufferedImageTest(){
        BufferedImage newImage = imageService.complexImageToBufferedImage(complexImage);
        Assertions.assertThat(newImage).isEqualTo(image.buffuredImageToGrayImage());
    }
}
