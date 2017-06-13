package com.rodrigofrazao.domain.supportConstraints;

import java.util.Arrays;

import static java.lang.Math.round;

public class Mask {
    boolean[][] mask;
    int height;
    int width;

    public Mask(int width, int height) {
        this.width = width;
        this.height = height;
        this.mask = new boolean[height][width];
    }

    public Mask(boolean[][] binaryImage){
        this.mask=binaryImage;
        height=binaryImage.length;
        width=binaryImage[0].length;
    }

    public boolean[][] getMask() {
        return mask;
    }

    public Mask ones() {
        for(int j = 0; j < height; ++j) {
            for(int k = 0; k < width; ++k) {
                mask[j][k] = true;
            }
        }
        return this;
    }

    public boolean[][] standardMask(){
        for(int j = height/4-1; j < round((double)height*(3.0/4.0)); ++j) {
            for(int k = width/4-1; k < round((double)width*(3.0/4.0)); ++k) {
                mask[j][k] = true;
            }
        }
        return this.getMask();
    }


}
