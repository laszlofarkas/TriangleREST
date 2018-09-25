package com.flac.triangle.rest.validator;

import com.flac.triangle.rest.model.Triangle;
import com.flac.triangle.rest.validator.annotation.TriangleSideConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TriangleSideValidator implements ConstraintValidator<TriangleSideConstraint, Triangle> {

    /**
     * Validate whether all sides of the triangle are less than the other two side
     *
     * @param triangle                   to be validate
     * @param constraintValidatorContext context of validator
     * @return true if triangle is valid
     */
    @Override
    public boolean isValid(Triangle triangle, ConstraintValidatorContext constraintValidatorContext) {
        return (triangle.getA() < triangle.getB() + triangle.getC()) &&
            (triangle.getB() < triangle.getA() + triangle.getC()) &&
            (triangle.getC() < triangle.getA() + triangle.getB());
    }
}
