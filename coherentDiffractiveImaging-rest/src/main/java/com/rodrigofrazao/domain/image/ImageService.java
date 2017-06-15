package com.rodrigofrazao.domain.image;



import com.rodrigofrazao.domain.complexNumbers.Complex;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
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
        BufferedImage image = new BufferedImage(height,width, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = image.getRaster();
        raster.setPixels(0, 0, height, width, result);
        image.setData(raster);
        return image;
    }

    public ComplexImage frontEndImageToComplexImage(BufferedImage bufferedImage) throws IOException {
        ReadImage image = new ReadImage(bufferedImage);
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
