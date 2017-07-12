package com.rodrigofrazao.domain.imageTest;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ImageService;
import com.rodrigofrazao.domain.image.ReadImage;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageServiceTest {

    ComplexImage complexImage,fourierImage;
    ReadImage image;
    ImageService imageService = new ImageService();

    @Before
    public void setUp() throws ExecutionException, InterruptedException {
        File f = new File("C:\\Users\\rodrpmff\\CDI\\images\\oi.jpg");
        image = new ReadImage(f);
        complexImage = image.bufferedImageToComplexImage();
      //  fourierImage = imageService.fft(complexImage);
    }

    @Test
    public void complexImageToBufferedImageTest() throws IOException {
        BufferedImage newImage = imageService.complexImageToBufferedImage(complexImage);
        File outputfile = new File("C:\\Users\\rodrpmff\\CDI\\images\\capture.PNG");
        ImageIO.write(newImage, "png", outputfile);
    }

    @Test
    public void logTest() {
        assertThat(imageService.logAndScaleFFT(fourierImage).getAmplitude()).doesNotContainNull();
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
