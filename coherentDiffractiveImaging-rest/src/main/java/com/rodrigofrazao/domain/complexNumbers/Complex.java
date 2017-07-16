package com.rodrigofrazao.domain.complexNumbers;

public class Complex {

    private final double re;
    private final double im;

    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }

    public String toString() {
        return this.im == 0.0D?this.re + "":(this.re == 0.0D?this.im + "i":(this.im < 0.0D?this.re + " - " + -this.im + "i":this.re + " + " + this.im + "i"));
    }

    public double abs() {
        return Math.hypot(this.re, this.im);
    }

    public double phase() {
        return Math.atan2(this.im, this.re);
    }

    public Complex plus(Complex b) {
        double real = this.re + b.re;
        double imag = this.im + b.im;
        return new Complex(real, imag);
    }

    public Complex minus(Complex b) {
        double real = this.re - b.re;
        double imag = this.im - b.im;
        return new Complex(real, imag);
    }

    public Complex times(Complex b) {
        double real = this.re * b.re - this.im * b.im;
        double imag = this.re * b.im + this.im * b.re;
        return new Complex(real, imag);
    }

    public Complex scale(double alpha) {
        return new Complex(alpha * this.re, alpha * this.im);
    }

    public Complex conjugate() {
        return new Complex(this.re, -this.im);
    }

    public Complex reciprocal() {
        double scale = this.re * this.re + this.im * this.im;
        return new Complex(this.re / scale, -this.im / scale);
    }

    public double re() {
        return this.re;
    }

    public double im() {
        return this.im;
    }

    public Complex divides(Complex b) {
        return this.times(b.reciprocal());
    }

    public Complex exp() {
        return new Complex(Math.exp(this.re) * Math.cos(this.im), Math.exp(this.re) * Math.sin(this.im));
    }

    public Complex sin() {
        return new Complex(Math.sin(this.re) * Math.cosh(this.im), Math.cos(this.re) * Math.sinh(this.im));
    }

    public Complex cos() {
        return new Complex(Math.cos(this.re) * Math.cosh(this.im), -Math.sin(this.re) * Math.sinh(this.im));
    }

    public Complex tan() {
        return this.sin().divides(this.cos());
    }

    public static Complex plus(Complex a, Complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Complex sum = new Complex(real, imag);
        return sum;
    }

    public static double[] reArray(Complex[] array) {
      double[] result = new double[array.length];
        for (int i = 0 ; i < array.length;++i){
            result[i] = array[i].re();
        }
        return result;
    }

    public static double[] imArray(Complex[] array) {
        double[] result = new double[array.length];
        for (int i = 0 ; i <array.length;i++){
            result[i] = array[i].im();
        }
        return result;
    }

    public  static Complex[] toComplexArray(double[] real,double[] imag){
        Complex[] result = new Complex[real.length];
        for (int i = 0 ; i <real.length;i++){
            result[i] = new Complex(real[i],imag[i]);
        }
        return result;
    }
}
