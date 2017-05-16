package com.rodrigofrazao.domain.image;

import com.rodrigofrazao.domain.complexNumbers.PolarComplex;

import java.util.Arrays;

public class ComplexImage {
    private double[][] amplitude;
    private double[][] phase;
    private double[][] real;
    private double[][] imaginary;
    int height = this.getHeight();
    int width = this.getWidth();

    public ComplexImage() {
    }

    public ComplexImage(double[][] amplitude, double[][] phase) {
        this.amplitude = amplitude;
        this.phase = phase;
        this.toCartesian();
    }

    public ComplexImage addRandomPhase() {
        double[][] randomPhase = new double[this.width][this.height];

        for(int j = 0; j < this.width; ++j) {
            for(int k = 0; k < this.height; ++k) {
                randomPhase[j][k] = -1.5707963267948966D + Math.random() * 3.141592653589793D / 2.0D;
            }
        }

        this.phase = randomPhase;
        return this;
    }

    public void toCartesian() {
        double[][] realArray = new double[this.width][this.height];
        double[][] imaginaryArray = new double[this.width][this.height];

        for(int j = 1; j < this.width; ++j) {
            for(int k = 1; k < this.height; ++k) {
                PolarComplex polarComplex = new PolarComplex(this.amplitude[j][k], this.phase[j][k]);
                realArray[j][k] = polarComplex.re();
                imaginaryArray[j][k] = polarComplex.im();
            }
        }

        this.real = realArray;
        this.imaginary = imaginaryArray;
    }

    public double[][] re() {
        return this.real;
    }

    public double[][] im() {
        return this.imaginary;
    }

    public double[][] getAmplitude() {
        return this.amplitude;
    }

    public ComplexImage setAmplitude(double[][] amplitude) {
        this.amplitude = amplitude;
        return this;
    }

    public double[][] getPhase() {
        return this.phase;
    }

    public void setPhase(double[][] phase) {
        this.phase = phase;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            ComplexImage that = (ComplexImage)o;
            return !Arrays.deepEquals(this.amplitude, that.amplitude)?false:Arrays.deepEquals(this.phase, that.phase);
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = Arrays.deepHashCode(this.amplitude);
        result = 31 * result + Arrays.deepHashCode(this.phase);
        return result;
    }

    public int getHeight() {
        return this.amplitude.length;
    }

    public int getWidth() {
        return this.amplitude[0].length;
    }
}
