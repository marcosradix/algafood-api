package br.com.workmade.core.utils;

import br.com.workmade.domain.model.Restaurante;
import br.com.workmade.exceptions.ValidacaoException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

public class Utils {

    public static void validate(Restaurante restaurante, String objectName, SmartValidator validator) {
        Object target;
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindingResult);
        if(bindingResult.hasErrors()){
            throw new ValidacaoException(bindingResult);
        }
    }
}
