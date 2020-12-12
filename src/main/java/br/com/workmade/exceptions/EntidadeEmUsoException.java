package br.com.workmade.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends RuntimeException {

	private static final long serialVersionUID = -5310311961324041391L;

	public EntidadeEmUsoException(String msg) {
		super(msg);
	}
	
	public EntidadeEmUsoException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}

