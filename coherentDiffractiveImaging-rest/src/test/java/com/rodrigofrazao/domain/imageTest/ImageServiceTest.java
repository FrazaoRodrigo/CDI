package com.rodrigofrazao.domain.imageTest;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import com.rodrigofrazao.domain.image.ReadImage;
import com.rodrigofrazao.domain.supportConstraints.Mask;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ImageServiceTest {

    ImageService imageService = new ImageService();
    ReadImage readImage;

    @Before
    public void setUp(){
        File f = new File("C:\\Users\\rodrpmff\\CDI\\images\\example.bmp");
        readImage = new ReadImage(f);
    }


    @Test
    public void onesMaskTest_whenAppliedAsConstraint_shouldReturnTheSameImage(){
        ComplexImage image =readImage.bufferedImageToComplexImage();
        Mask onesMask = imageService.getOnesMask(image);
        Assertions.assertThat(imageService.withSupportConstraint(image,onesMask)).isEqualTo(image);
    }

    @Test
    public void fftTest(){
        ComplexImage image =readImage.bufferedImageToComplexImage();
        Assertions.assertThat(imageService.fft(image)).isNotNull();
    }
}
