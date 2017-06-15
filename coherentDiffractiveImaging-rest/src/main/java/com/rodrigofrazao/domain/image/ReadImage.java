package com.rodrigofrazao.domain.image;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadImage {

    BufferedImage image;

    public ReadImage(File imagePath) {
        try {
            this.image = ImageIO.read(imagePath);
        } catch (IOException er) {
            System.err.println("IOException: " + er.getMessage());
        }
    }

    public ReadImage(BufferedImage bufferedImage) throws IOException {
    this.image=bufferedImage;
    }

    public BufferedImage getBuffuredImage(){return image;}

    public BufferedImage buffuredImageToGrayImage(){
       BufferedImage gray = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        ColorConvertOp op = new ColorConvertOp(
                image.getColorModel().getColorSpace(),
                gray.getColorModel().getColorSpace(),null);
        image= op.filter(image,gray);
        return image;
    }

    public double[][] bufferedImageToArray() {
        int w = getWidth();
        int h = getHeight();
        double[][] array = new double[w][h];
        buffuredImageToGrayImage();
        Raster raster = image.getData();
        for(int j = 0; j < w; ++j) {
            for(int k = 0; k < h; ++k) {
                array[j][k] = raster.getSampleDouble(j, k,0 );
            }
        }

        return array;
    }

    public ComplexImage bufferedImageToComplexImage() {
        return new AmplitudeOnlyImage(bufferedImageToArray());
    }

    public BufferedImage toSave() {
        return this.image;
    }

    public void displayImage() {
        this.image.getGraphics();
    }

    public static void saveToFile(BufferedImage img, String string) throws FileNotFoundException, IOException {
        File outputfile = new File(string);
        ImageIO.write(img, "png", outputfile);
    }

    public int getWidth() {return this.image.getWidth();}

    public int getHeight() {
        return this.image.getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadImage image1 = (ReadImage) o;

        return image != null ? image.equals(image1.image) : image1.image == null;
    }

    @Override
    public int hashCode() {
        return image != null ? image.hashCode() : 0;
    }
}
