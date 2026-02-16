package br.nicolas.remembering.annotation;

import br.nicolas.remembering.annotation.constraint.GradeListValidationConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target (ElementType.FIELD)
@Retention (RetentionPolicy.RUNTIME)
@Constraint (validatedBy = GradeListValidationConstraint.class )
public @interface GradeListValidation {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
