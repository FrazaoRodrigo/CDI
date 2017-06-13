package com.rodrigofrazao.domain.image;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Arrays;


public class ImageService {

    public Image guessToImageDisplay(ComplexImage pixels) {
        int height = pixels.getAmplitude().length;
        int width = pixels.getAmplitude()[0].length;
        double[] result = Arrays.stream(pixels.getAmplitude()).flatMapToDouble(Arrays::stream).toArray();
        BufferedImage image = new BufferedImage(height,width, 10);
        WritableRaster raster = (WritableRaster)image.getData();
        raster.setPixels(0, 0,  height,width, result);
        return image;
    }







}
