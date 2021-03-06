package com.rodrigofrazao.domain.image;

import com.rodrigofrazao.domain.complexNumbers.Complex;
import com.rodrigofrazao.domain.complexNumbers.PolarComplex;

import com.rodrigofrazao.domain.supportConstraints.Mask;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.rodrigofrazao.domain.fourierTransform.CT_TwoD_FFT.fft_twoD_ct;
import static com.rodrigofrazao.domain.fourierTransform.CT_TwoD_Inverse_FFT.ifft_ct;
import static com.rodrigofrazao.domain.fourierTransform.Fftshift.shiftOrigin;
import static com.rodrigofrazao.domain.fourierTransform.Ifftshift.iShiftOrigin;
import static com.rodrigofrazao.domain.fourierTransform.InverseTwoD_FFT.inverseXform2D;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.twoDFFT_inline_thread;
import static com.rodrigofrazao.domain.fourierTransform.ThreadService.twoDFFT_thread;
import static com.rodrigofrazao.domain.fourierTransform.TwoD_FFT.twoDfft;

public class ComplexImage {
    private double[][] amplitude, phase;
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

    public double[][] compareAmplitude() {
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                amplitude[j][k] = (double)Math.round(amplitude[j][k] * 1000d) / 1000d;
            }
        }
        return this.getAmplitude();
    }

    public Complex toComplex(int height, int width) {
        PolarComplex polarComplex = new PolarComplex(amplitude[height][width], phase[height][width]);
        return new Complex(polarComplex.re(), polarComplex.im());
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

    public ComplexImage invfft() throws ExecutionException, InterruptedException {
        return ifft_ct(this,10);
    }

    public ComplexImage fft() throws ExecutionException, InterruptedException {
        return fft_twoD_ct(this,10);
    }

    public ComplexImage shift() {
        return new AmplitudeOnlyImage(shiftOrigin(amplitude));
    }

    public ComplexImage shiftWithPhase() {
        this.amplitude = shiftOrigin(amplitude);
        this.phase = shiftOrigin(phase);
        return this;
    }

    public ComplexImage iShiftWithPhase(){
        this.amplitude= iShiftOrigin(amplitude);
        this.phase =iShiftOrigin(phase);
        return this;
    }

    public ComplexImage fft_thread() throws InterruptedException, ExecutionException {
        return twoDFFT_thread(amplitude, 10);
    }

    public ComplexImage fft_inline_thread() throws InterruptedException, ExecutionException {
        return twoDFFT_inline_thread(this, 10);
    }


    public ComplexImage fft_CP_thread_2V() throws ExecutionException, InterruptedException {
        return fft_twoD_ct(this, 10);
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

        return new ComplexImage(array,phase);
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

    public ComplexImage sumComplex(ComplexImage image) {

        for (int j = 0; j < height; ++j) {
            for (int k = 0; k < width; ++k) {
                amplitude[j][k] = amplitude[j][k] + image.getAmplitude()[j][k];
                phase[j][k] = phase[j][k] + image.getPhase()[j][k];
            }
        }
        return this;
    }

    private ComplexImage paddWithZeros(int numOfPadsHeight, int numOfPadsWidth) {
        double[][] outAmplitude = new double[height + numOfPadsHeight][width + numOfPadsWidth];
        double[][] outPhase = new double[height + numOfPadsHeight][width + numOfPadsWidth];

        if (numOfPadsHeight % 2 == 0 && numOfPadsWidth % 2 == 0) {
            numOfPadsHeight=numOfPadsHeight/2;
            numOfPadsWidth=numOfPadsWidth/2;
        } else if (numOfPadsHeight % 2 != 0 && numOfPadsWidth % 2 == 0) {
            numOfPadsWidth=numOfPadsWidth/2;
            numOfPadsHeight=numOfPadsHeight/2+1;
        } else if (numOfPadsHeight % 2 == 0 && numOfPadsWidth % 2 != 0) {
            numOfPadsHeight=numOfPadsHeight/2;
            numOfPadsWidth=numOfPadsWidth/2+1;
        } else {
            numOfPadsHeight=numOfPadsHeight/2+1;
            numOfPadsWidth=numOfPadsWidth/2+1;
        }

        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                outAmplitude[j + numOfPadsHeight][k + numOfPadsWidth] = amplitude[j][k];
                outPhase[j + numOfPadsHeight][k + numOfPadsWidth] = phase[j][k];
            }
        }
        return new ComplexImage(outAmplitude, outPhase);
    }

    public ComplexImage checkImageDimensions() {
        int newHeight = height;
        int newWidth = width;
        if ((height & (height - 1)) != 0) {
            newHeight = nextPowerOfTwo(height);
        }
        if ((width & (width - 1)) != 0) {
            newWidth = nextPowerOfTwo(width);
        }
        if ((height & (height - 1)) == 0 && (width & (width - 1)) == 0) {
            return this;
        } else {
            return paddWithZeros((newHeight - height), (newWidth - width));
        }
    }

    private int nextPowerOfTwo(int number) {
        int highestOneBit = Integer.highestOneBit(number);
        if (highestOneBit < number) {
            highestOneBit = highestOneBit * 2;
        }
        return highestOneBit;
    }

    @Override
    public String toString(){
        StringBuffer amplitudeString = new StringBuffer();

        for (int i = 0; i < amplitude.length; i++) {
            for (int j = 0; j < amplitude[0].length; j++) {
                amplitudeString.append(amplitude[i][j] + " ");
            }
            amplitudeString.append("/n");
        }
        return amplitudeString.toString();
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


}
