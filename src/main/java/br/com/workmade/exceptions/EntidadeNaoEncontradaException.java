package br.com.workmade.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -5310311961324041391L;

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public EntidadeNaoEncontradaException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}

