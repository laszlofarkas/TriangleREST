package com.flac.triangle.rest.model;

import javax.validation.constraints.Positive;

public class Triangle {

    @Positive
    private Double a;

    @Positive
    private Double b;

    @Positive
    private Double c;

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }

    public Double getC() {
        return c;
    }
}
