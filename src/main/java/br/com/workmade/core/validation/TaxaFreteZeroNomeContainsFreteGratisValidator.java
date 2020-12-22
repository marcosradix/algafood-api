package br.com.workmade.core.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

@Slf4j
public class TaxaFreteZeroNomeContainsFreteGratisValidator implements ConstraintValidator<TaxaFreteZeroNomeContainsFreteGratis, Object> {

    private String sourceField;
    private String targetField;
    private String targetContains;

    @Override
    public void initialize(TaxaFreteZeroNomeContainsFreteGratis constraint) {
        this.sourceField = constraint.sourceField();
        this.targetField = constraint.targetField();
        this.targetContains = constraint.targetContains();
    }


    @Override
    public boolean isValid(Object objectToValidate, ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objectToValidate.getClass(), this.sourceField)
                    .getReadMethod().invoke(objectToValidate);

            String targetField = (String) BeanUtils.getPropertyDescriptor(objectToValidate.getClass(), this.targetField)
                    .getReadMethod().invoke(objectToValidate);

            if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && targetField != null) {
                valid = targetField.toLowerCase().contains(this.targetContains.toLowerCase());
            }
            return valid;
        } catch (Exception e) {
            //log.info(ExceptionUtils.getStackTrace(e));
            throw new ValidationException(e);
        }
    }
}
