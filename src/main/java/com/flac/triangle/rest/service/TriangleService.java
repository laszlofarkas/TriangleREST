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
        if (triangle.getA() == triangle.getB()) {
            if (triangle.getA() == triangle.getC()) {
                return TriangleType.EQUILATERAL;
            } else {
                return TriangleType.ISOSCELES;
            }
        }
        if (triangle.getA() == triangle.getC() || triangle.getB() == triangle.getC()) {
            return TriangleType.ISOSCELES;
        }
        return TriangleType.SCALENE;
    }
}
