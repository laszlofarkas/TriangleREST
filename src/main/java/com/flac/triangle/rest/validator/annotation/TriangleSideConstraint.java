package com.flac.triangle.rest.validator.annotation;

import com.flac.triangle.rest.validator.TriangleSideValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Validator annotation for check whether all sides of the triangle are less than the other two side
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TriangleSideValidator.class)
public @interface TriangleSideConstraint {
    String message() default "All sides of the triangle should be less then the sum of other two sides";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
