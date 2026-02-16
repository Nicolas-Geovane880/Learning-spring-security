package br.nicolas.remembering.annotation.constraint;

import br.nicolas.remembering.annotation.UniqueEmailCreation;
import br.nicolas.remembering.service.EmailService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailCreationConstraint implements ConstraintValidator<UniqueEmailCreation, String> {

    @Autowired
    private EmailService emailService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        // When the email is being used, the check method will return true
        // So this method will return false, meaning the email can not be used anymore (is 'not valid')
        return !emailService.checkIfEmailIsAlreadyInUse(email);
    }
}
