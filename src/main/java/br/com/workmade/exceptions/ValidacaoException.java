package br.com.workmade.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = -5310311961324041391L;
	private BindingResult bindingResult;


	
}

