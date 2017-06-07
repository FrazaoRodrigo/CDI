package com.rodrigofrazao.domain.supportConstraints;

import java.util.Arrays;

public class Mask {
    boolean[][] mask;
    int height;
    int width;

    public Mask(int width, int height) {
        this.width = width;
        this.height = height;
        this.mask = new boolean[height][width];
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
}
