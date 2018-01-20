package com.rodrigofrazao.domain.supportConstraintsTest.filterTest;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.supportConstraints.filters.FilterService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * Created by rodrigofrazao on 16/09/2017.
 */
public class filterServiceTest {
    ComplexImage image;
    double[][] testArray = new double[8][4];
    FilterService filterService = new FilterService();

    @Before
    public void setUp(){
        for(int j = 0; j < 8; ++j) {
            for(int k = 0; k < 4; ++k) {
                testArray[j][k] =  Math.random() * 256;
            }
        }
        image= new ComplexImage(testArray,testArray);
    }

    @Test
    public void lowPassFilterTest_whenRadiusIsZero_ShouldBeEqual() throws ExecutionException, InterruptedException {
        Assertions.assertThat(filterService
                .lowpassFilter(image,0)
                .compareAmplitude())
                .isEqualTo(image
                        .compareAmplitude());
    }
}
