package com.rodrigofrazao.domain.image;



import com.rodrigofrazao.domain.complexNumbers.Complex;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;

import static com.rodrigofrazao.domain.fourierTransform.TwoD_FFT.twoDfft;
import static java.util.Base64.getEncoder;


public class ImageService {

    public BufferedImage complexImageToBufferedImage(ComplexImage pixels) {
        int height = pixels.getAmplitude().length;
        int width = pixels.getAmplitude()[0].length;
        double[] result = Arrays.stream(pixels.getAmplitude()).flatMapToDouble(Arrays::stream).toArray();
        BufferedImage image = new BufferedImage(height,width, 10);
        WritableRaster raster = (WritableRaster)image.getData();
        raster.setPixels(0, 0,  height,width, result);
        return image;
    }

    public ComplexImage frontEndImageToComplexImage(InputStream inputStream) throws IOException {
        ReadImage image = new ReadImage(inputStream);
        return image.bufferedImageToComplexImage();
    }


    public ComplexImage fft(ComplexImage image){
      return  twoDfft(image.getAmplitude());
    }

    public String convertComplexImageToFrontEnd(ComplexImage image) throws IOException {
        BufferedImage bufferedImage = complexImageToBufferedImage(image);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.flush();
        ImageIO.write(bufferedImage,"png",baos);
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

}
