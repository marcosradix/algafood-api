package br.com.workmade.core.validation;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.math.BigDecimal;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { MultiploValidator.class})
public @interface Multiplo {
    int numero();
    @OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
    String message() default "Não é um multiplo válido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };



}
