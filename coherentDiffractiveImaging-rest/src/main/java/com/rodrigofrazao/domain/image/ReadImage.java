package com.rodrigofrazao.domain.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadImage {
    BufferedImage image;

    public ReadImage(File imagePath) {
        try {
            this.image = ImageIO.read(imagePath);
        } catch (IOException var3) {
            System.err.println("IOException: " + var3.getMessage());
        }

    }

    public BufferedImage toSave() {
        return this.image;
    }

    public int getWidth() {
        return this.image.getWidth();
    }

    public void displayImage() {
        this.image.getGraphics();
    }

    public static void saveToFile(BufferedImage img, String string) throws FileNotFoundException, IOException {
        File outputfile = new File(string);
        ImageIO.write(img, "png", outputfile);
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    public Raster getData() {
        return this.image.getData();
    }
}
