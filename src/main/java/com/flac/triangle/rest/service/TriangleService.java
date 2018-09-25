package com.flac.triangle.rest.service;

import com.flac.triangle.rest.model.Triangle;
import com.flac.triangle.rest.model.TriangleType;
import org.springframework.stereotype.Service;

@Service
public class TriangleService {

    /**
     * Determine the type of the given triangle
     *
     * @param triangle to be check
     * @return type of the given triangle
     */
    public TriangleType getTriangleType(Triangle triangle) {
        // all sides are equal
        if (triangle.getA() == triangle.getB() && triangle.getA() == triangle.getC()) {
            return TriangleType.EQUILATERAL;
        }
        // two of sides are equal
        if (triangle.getA() == triangle.getB() || triangle.getA() == triangle.getC() || triangle.getB() == triangle.getC()) {
            return TriangleType.ISOSCELES;
        }
        // all sides are different
        return TriangleType.SCALENE;
    }
}
