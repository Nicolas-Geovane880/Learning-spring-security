package br.nicolas.remembering.annotation.constraint;

import br.nicolas.remembering.annotation.GradeListValidation;
import br.nicolas.remembering.constant.ConstantValues;
import br.nicolas.remembering.constant.ErrorMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class GradeListValidationConstraint implements ConstraintValidator<GradeListValidation, List<Object>> {

    @Override
    public boolean isValid(List<Object> grades, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        if (grades == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorMessage.FIELD_NOT_NULL)
                    .addConstraintViolation();
            return false;
        }

        if (grades.size() != ConstantValues.MAXIMUM_GRADES_PER_STUDENT) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorMessage.STUDENT_MUST_HAVE_3_GRADES)
                    .addConstraintViolation();
            return false;
        }

        for (Object gradeObj : grades) {
            if (!(gradeObj instanceof Double)) {
                constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorMessage.GRADE_MUST_BE_FLOAT_NUMBER)
                        .addConstraintViolation();
                return false;
            }
        }

        for (Double gradeObj : grades.stream().map(o -> (Double)o).toList()) {
            if (gradeObj < ConstantValues.MINIMUM_GRADE || gradeObj > ConstantValues.MAXIMUM_GRADE) {
                constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorMessage.INVALID_GRADES)
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
