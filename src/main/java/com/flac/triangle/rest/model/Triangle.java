package com.flac.triangle.rest.model;

import com.flac.triangle.rest.validator.annotation.TriangleSideConstraint;

import javax.validation.constraints.Positive;

@TriangleSideConstraint
public class Triangle {

    @Positive
    private double a;

    @Positive
    private double b;

    @Positive
    private double c;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
}
