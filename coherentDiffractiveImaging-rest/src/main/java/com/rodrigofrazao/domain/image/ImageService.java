package com.rodrigofrazao.domain.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

import static com.rodrigofrazao.domain.fourierTransform.TwoD_FFT.twoDfft;
import static javax.xml.bind.DatatypeConverter.*;

public class ImageService {

    public BufferedImage decodeImageFromFrontEnd(String imageString) throws IOException {
        String base64Image = imageString.split(",")[0];
        byte[] imageBytes = parseBase64Binary(base64Image);
        return ImageIO.read(new ByteArrayInputStream(imageBytes));
    }

    public ComplexImage frontEndImageToComplexImage(BufferedImage bufferedImage) throws IOException {
        if (bufferedImage == null){
            throw new InvalidObjectException("Base64 image is wrong results in a NULL Buffured image");
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
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                int rgb = (int) pixels.getAmplitude()[x][y] << 16 | (int) pixels.getAmplitude()[x][y] << 8 | (int) pixels.getAmplitude()[x][y];
                b.setRGB(x, y, rgb);
            }
        }
        return b;
    }

    public ComplexImage fft(ComplexImage image) {
        return twoDfft(image.getAmplitude());
    }

}
