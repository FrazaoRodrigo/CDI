package com.rodrigofrazao.domain.supportConstraints;

import java.util.Arrays;

public class Mask {
    boolean[][] mask;
    int height;
    int width;

    public Mask(int width, int height) {
        this.width = width;
        this.height = height;
        this.mask = new boolean[width][height];
    }

    public boolean[][] getMask() {
        return this.mask;
    }

    public boolean[][] ones() {
        Arrays.fill(this.mask, Boolean.valueOf(true));
        return this.mask;
    }
}
