package com.flac.triangle.rest.model;

import com.flac.triangle.rest.validator.annotation.TriangleSideConstraint;

import javax.validation.constraints.Positive;

@TriangleSideConstraint
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

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }
}
