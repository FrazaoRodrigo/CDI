package com.rodrigofrazao.domain.image;

import com.rodrigofrazao.domain.fourierTransform.InverseTwoD_FFT;
import com.rodrigofrazao.domain.fourierTransform.TwoD_FFT;
import com.rodrigofrazao.domain.supportConstraints.Mask;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Arrays;

public class ImageService {
    InverseTwoD_FFT inversetwoD_fft = new InverseTwoD_FFT();
    TwoD_FFT twoD_fft = new TwoD_FFT();
    Mask mask;

    public ImageService() {
    }

    public boolean[][] getMask(ComplexImage image) {
        this.mask = new Mask(image.width, image.height);
        return this.mask.getMask();
    }

    public boolean[][] getOnesMask(ComplexImage image) {
        this.mask = new Mask(image.width, image.height);
        return this.mask.ones();
    }

    public ComplexImage bufferedImageToComplexImage(ReadImage image) {
        return new ComplexImage(this.bufferedImageToArray(image), null);
    }

    public Image guessToImageDisplay(ComplexImage pixels) {
        int width = pixels.getWidth();
        int height = pixels.getHeight();
        double[] result = Arrays.stream(pixels.getAmplitude()).flatMapToDouble(Arrays::stream).toArray();
        BufferedImage image = new BufferedImage(width, height, 10);
        WritableRaster raster = (WritableRaster)image.getData();
        raster.setPixels(0, 0, width, height, result);
        return image;
    }

    public double[][] bufferedImageToArray(ReadImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        double[][] array = new double[w][h];
        Raster raster = image.getData();

        for(int j = 0; j < w; ++j) {
            for(int k = 0; k < h; ++k) {
                array[j][k] = (double)raster.getSample(j, k, 0);
            }
        }

        return array;
    }

    public ComplexImage invfft(ComplexImage image) {
        return this.inversetwoD_fft.setInputData(image.re(), image.im()).inverseXform2D();
    }

    public ComplexImage withSupportConstraint(ComplexImage realPlaneImage, boolean[][] mask) {
        return this.applyRealSpaceSupport(realPlaneImage, mask);
    }

    private ComplexImage applyRealSpaceSupport(ComplexImage amplitude, boolean[][] mask) {
        int h =amplitude.getHeight();
        int w=amplitude.getWidth();

        double[][] array = new double[w][h];

        for(int j = 0; j < w; ++j) {
            for(int k = 0; k < h; ++k) {
                int myInt = mask[j][k]?1:0;
                array[j][k] = amplitude.getAmplitude()[j][k] * (double)myInt;
            }
        }

        return new ComplexImage(array, (double[][])null);
    }

    public ComplexImage fft(ComplexImage realPlaneImageWithSupport) {
        return this.twoD_fft.setInputData(realPlaneImageWithSupport.getAmplitude()).twoDfft();
    }
}
