package com.rodrigofrazao.domain.image;

import com.rodrigofrazao.domain.complexNumbers.PolarComplex;
import com.rodrigofrazao.domain.fourierTransform.InverseTwoD_FFT;
import com.rodrigofrazao.domain.supportConstraints.Mask;

import java.util.Arrays;

import static com.rodrigofrazao.domain.fourierTransform.InverseTwoD_FFT.inverseXform2D;
import static com.rodrigofrazao.domain.fourierTransform.TwoD_FFT.twoDfft;

public class ComplexImage {
    double[][] amplitude,phase;
    int height,width;

    public ComplexImage(){}

    public ComplexImage(double[][] amplitude, double[][] phase) {
        this.amplitude = amplitude;
        this.height = amplitude.length;
        this.width = amplitude[0].length;
        this.phase = phase;
    }

    public ComplexImage addRandomPhase() {
        double[][] randomPhase = new double[height][width];

        for(int j = 0; j < height; ++j) {
            for(int k = 0; k < width; ++k) {
                randomPhase[j][k] = -1.5707963267948966D + Math.random() * 3.141592653589793D / 2.0D;
            }
        }
        phase = randomPhase;
        return this;
    }

    public double[][] re() {
        double[][] realArray = new double[height][width];
        for(int j = 0; j < height; j++) {
            for(int k = 0; k < width; k++) {
                PolarComplex polarComplex = new PolarComplex(amplitude[j][k], phase[j][k]);
                realArray[j][k] = polarComplex.re();
            }
        }
        return realArray;
    }

    public double[][] im() {
        double[][] imaginaryArray = new double[height][width];
        for(int j = 0; j < height; ++j) {
            for(int k = 0; k < width; ++k) {
                PolarComplex polarComplex = new PolarComplex(this.amplitude[j][k], this.phase[j][k]);
                imaginaryArray[j][k] = polarComplex.im();
            }
        }
        return imaginaryArray;
    }

    public ComplexImage invfft() {
       return inverseXform2D(re(),im());
    }

    public ComplexImage fft(ComplexImage realPlaneImageWithSupport) {
         return twoDfft(realPlaneImageWithSupport.getAmplitude());
    }



    public double[][] getAmplitude() {
        return amplitude;
    }

    public double[][] getPhase(){
        return phase;
    }

    public ComplexImage setAmplitude(double[][] amplitude) {
        this.amplitude = amplitude;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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

}
