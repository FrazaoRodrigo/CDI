package com.rodrigofrazao.domain.supportConstraints.filters;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.supportConstraints.Mask;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static com.rodrigofrazao.domain.supportConstraints.filters.Gaussian.inverseCDF;

/**
 * Created by rodrigofrazao on 16/09/2017.
 */
@Service
public class FilterService {

    public ComplexImage lowpassFilter(ComplexImage image, int radius) throws ExecutionException, InterruptedException {
        Mask mask = new Mask(image.getWidth(), image.getHeight());
        mask.lowpassfilter(radius);

        return image
                .fft()
                .shiftWithPhase()
                .withInverseSupportConstraint(mask)
                .iShiftWithPhase()
                .invfft();

    }


    public ComplexImage gaussian(ComplexImage image) {

        double[][] array = new double[image.height][image.width];
        for (int i = 0; i < image.getAmplitude().length; i++) {
            for (int j = 0; j < image.getAmplitude()[0].length; j++) {
                array[i][j]=inverseCDF(image.amplitude[i][j]);
            }
        }
        return image.setAmplitude(array);
    }


}