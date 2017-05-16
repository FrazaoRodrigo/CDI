package com.rodrigofrazao.domain.complexNumbers;

public class PolarComplex extends Complex {

    private final double amp;
    private final double phase;

    public PolarComplex(double amp, double phase) {
        super(amp * Math.cos(phase), amp * Math.sin(phase));
        this.amp = amp;
        this.phase = phase;
    }
}
