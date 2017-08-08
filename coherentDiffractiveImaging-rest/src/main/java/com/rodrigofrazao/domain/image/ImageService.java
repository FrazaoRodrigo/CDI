package com.rodrigofrazao.domain.image;

import com.rodrigofrazao.domain.supportConstraints.Mask;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

import static com.rodrigofrazao.domain.fourierTransform.CT_TwoD_FFT.fft_twoD_ct;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.inverseTwoDFFT_thread;
import static javax.xml.bind.DatatypeConverter.*;

public class ImageService {

    public BufferedImage decodeImageFromFrontEnd(String imageString) throws IOException {
        String base64Image = imageString.split(",")[0];
        byte[] imageBytes = parseBase64Binary(base64Image);
        return ImageIO.read(new ByteArrayInputStream(imageBytes));
    }

    public ComplexImage frontEndImageToComplexImage(BufferedImage bufferedImage) throws IOException {
        if (bufferedImage == null){
            throw new InvalidObjectException("Base64 image is wrong, results in a NULL Buffured image");
        }
        ReadImage image = new ReadImage(bufferedImage);
        return image.bufferedImageToComplexImage();
    }

    public String complexImageToFrontEnd(ComplexImage image) throws IOException {
        BufferedImage bufferedImage = complexImageToBufferedImage(image);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.flush();
        ImageIO.write(bufferedImage, "png", baos);
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public BufferedImage complexImageToBufferedImage(ComplexImage pixels) {
        int height = pixels.getAmplitude().length;
        int width = pixels.getAmplitude()[0].length;
        BufferedImage b = new BufferedImage(height, width, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = b.getRaster();
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                raster.setSample(x,y,0,pixels.getAmplitude()[x][y]);
            }
        }
        return b;
    }

    public ComplexImage fft(ComplexImage image) throws ExecutionException, InterruptedException {
        return  fft_twoD_ct(image,10);
    }

    public ComplexImage logAndScaleFFT(ComplexImage fourierTransformedImage)  {
        int height = fourierTransformedImage.getAmplitude().length;
        int width = fourierTransformedImage.getAmplitude()[0].length;
        double max = 0;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                fourierTransformedImage.getAmplitude()[x][y] = Math.log(fourierTransformedImage.getAmplitude()[x][y]+1);
                max =  (fourierTransformedImage.getAmplitude()[x][y] > max) ?  fourierTransformedImage.getAmplitude()[x][y] : max;
            }
        }
        return fourierTransformedImage;
    }

    public ComplexImage invfft(ComplexImage fourierImage) throws ExecutionException, InterruptedException {
        return inverseTwoDFFT_thread(fourierImage,10);
    }


    public ComplexImage lowpassfilter(ComplexImage fourierImage,int radius) {
        Mask mask = new Mask(fourierImage.getWidth(),fourierImage.getHeight());
         mask.lowpassfilter(radius);
        return fourierImage.withSupportConstraint(mask);
    }
}
