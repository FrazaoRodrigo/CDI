package com.rodrigofrazao.domain.imageTest;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.InlineComplexImage;
import com.rodrigofrazao.domain.image.ReadImage;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class InlineImageTest {

    double[] inlinetestArray;
    ReadImage image;

    @Before
    public void setUp() {
        File f = new File("C:\\Users\\rodrpmff\\CDI\\images\\example.bmp");
        this.image = new ReadImage(f);
    }

    @Test
    public void inlineComplexImage_isEqual_toComplexImage(){
        ComplexImage complexImage = image.bufferedImageToComplexImage();
        InlineComplexImage inlineComplexImage = complexImage.toInlineImage();
        Assertions.assertThat(complexImage.getAmplitude()).isEqualTo(inlineComplexImage.toComplexImage().getAmplitude());
    }
}
