package br.com.workmade.exceptions;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -5310311961324041391L;

	public CidadeNaoEncontradaException(String msg) {
		super(msg);
	}

	public CidadeNaoEncontradaException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}

