package com.rodrigofrazao.domain.image;



import com.rodrigofrazao.domain.complexNumbers.Complex;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.rodrigofrazao.domain.fourierTransform.TwoD_FFT.twoDfft;


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

    public ComplexImage getUploadedImageToComplexImage(File file){
       ReadImage image = new ReadImage(file);
       return image.bufferedImageToComplexImage();
    }

    public ComplexImage fft(ComplexImage image){
      return  twoDfft(image.getAmplitude());
    }

    public MultipartFile convertImageToFrontEnd(ComplexImage image, String imageName, String originalFileName, String contentType, long size) throws IOException {
        BufferedImage bufferedImage = complexImageToBufferedImage(image);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.flush();
        ImageIO.write(bufferedImage,"png",baos);
        MultipartFile multipartFile = new MultipartImage(baos.toByteArray(),imageName,originalFileName,contentType,size);
        return multipartFile;
    }

}
