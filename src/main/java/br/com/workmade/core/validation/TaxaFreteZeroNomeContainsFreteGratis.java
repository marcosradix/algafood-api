package br.com.workmade.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = {TaxaFreteZeroNomeContainsFreteGratisValidator.class})
public @interface TaxaFreteZeroNomeContainsFreteGratis {

    String message() default "Descrição inválida";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String sourceField();
    String targetField();
    String targetContains();
}
