package com.rodrigofrazao.domain.image;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.PolarComplex;
import com.rodrigofrazao.domain.fourierTransform.Threaded_TwoD_FFT;
import com.rodrigofrazao.domain.supportConstraints.Mask;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static com.rodrigofrazao.domain.fourierTransform.CT_TwoD_FFT.twoD_fft_ct;
import static com.rodrigofrazao.domain.fourierTransform.Fftshift.shiftOrigin;
import static com.rodrigofrazao.domain.fourierTransform.InverseTwoD_FFT.inverseXform2D;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.twoDFFT_inline_thread;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.twoDFFT_thread;
import static com.rodrigofrazao.domain.fourierTransform.TwoD_FFT.twoDfft;

public class ComplexImage {
    public double[][] amplitude, phase;
    public int height, width;

    public ComplexImage() {
    }

    public ComplexImage(double[][] amplitude, double[][] phase) {
        this.amplitude = amplitude;
        this.height = amplitude.length;
        this.width = amplitude[0].length;
        this.phase = phase;
    }

    public ComplexImage addRandomPhase() {
        double[][] randomPhase = new double[height][width];

        for (int j = 0; j < height; ++j) {
            for (int k = 0; k < width; ++k) {
                randomPhase[j][k] = -1.5707963267948966D + Math.random() * 3.141592653589793D / 2.0D;
            }
        }
        phase = randomPhase;
        return this;
    }

    public InlineComplexImage toInlineImage() {
        double[] outAmplitude = new double[height * width];
        double[] outPhase = new double[height * width];
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                outAmplitude[k + j * width] = amplitude[j][k];
                outPhase[k + j * width] = phase[j][k];
            }
        }
        return new InlineComplexImage(height, width, outAmplitude, outPhase);
    }

    public Complex[][] toComplexArray(){
        Complex[][] result = new Complex[height][width];
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
               result[j][k] = new Complex(re()[j][k],im()[j][k]);
            }
        }
        return result;
    }


    public double[][] re() {
        double[][] realArray = new double[height][width];
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                PolarComplex polarComplex = new PolarComplex(amplitude[j][k], phase[j][k]);
                realArray[j][k] = polarComplex.re();
            }
        }
        return realArray;
    }

    public double[][] im() {
        double[][] imaginaryArray = new double[height][width];
        for (int j = 0; j < height; ++j) {
            for (int k = 0; k < width; ++k) {
                PolarComplex polarComplex = new PolarComplex(this.amplitude[j][k], this.phase[j][k]);
                imaginaryArray[j][k] = polarComplex.im();
            }
        }
        return imaginaryArray;
    }

    public ComplexImage invfft() {
        return inverseXform2D(re(), im());
    }

    public ComplexImage fft() {
        return twoDfft(amplitude);
    }

    public ComplexImage shift() {
        return shiftOrigin(amplitude);
    }

    public ComplexImage shiftWithPhase() {
        amplitude = shiftOrigin(amplitude).getAmplitude();
        phase = shiftOrigin(phase).getPhase();
        return this;
    }

    public ComplexImage fft_thread() throws InterruptedException, ExecutionException {
        return twoDFFT_thread(amplitude, 10);
    }

    public ComplexImage fft_inline_thread() throws InterruptedException, ExecutionException {
        return twoDFFT_inline_thread(this, 10);
    }

    public ComplexImage fft_CP_thread() throws InterruptedException, ExecutionException {
        return twoD_fft_ct(this, 10);
    }


    public double[][] getAmplitude() {
        return amplitude;
    }

    public double[][] getPhase() {
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

    public ComplexImage withSupportConstraint(Mask mask) {
        double[][] array = new double[height][width];

        for (int j = 0; j < height; ++j) {
            for (int k = 0; k < width; ++k) {
                int myInt = mask.getMask()[j][k] ? 1 : 0;
                array[j][k] = amplitude[j][k] * (double) myInt;
            }
        }

        return new AmplitudeOnlyImage(array);
    }


    public ComplexImage withInverseSupportConstraint(Mask mask) {

        double[][] array = new double[height][width];

        for (int j = 0; j < height; ++j) {
            for (int k = 0; k < width; ++k) {
                int myInt = mask.getMask()[j][k] ? 0 : 1;
                array[j][k] = amplitude[j][k] * (double) myInt;
            }
        }

        return new AmplitudeOnlyImage(array);
    }

    public Mask toBinaryMask(double threshold) {
        boolean[][] binaryGuess = new boolean[height][width];

        for (int j = 0; j < height; ++j) {
            for (int k = 0; k < width; ++k) {
                boolean b = (amplitude[j][k] > threshold);
                binaryGuess[j][k] = b;
            }
        }
        return new Mask(binaryGuess);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexImage that = (ComplexImage) o;

        if (height != that.height) return false;
        if (width != that.width) return false;
        if (!Arrays.deepEquals(amplitude, that.amplitude)) return false;
        return Arrays.deepEquals(phase, that.phase);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(amplitude);
        result = 31 * result + Arrays.deepHashCode(phase);
        result = 31 * result + height;
        result = 31 * result + width;
        return result;
    }

    public ComplexImage sumComplex(ComplexImage image) {

        for (int j = 0; j < height; ++j) {
            for (int k = 0; k < width; ++k) {
                amplitude[j][k] = amplitude[j][k] + image.getAmplitude()[j][k];
                phase[j][k] = phase[j][k] + image.getPhase()[j][k];
            }
        }
        return this;
    }
}
