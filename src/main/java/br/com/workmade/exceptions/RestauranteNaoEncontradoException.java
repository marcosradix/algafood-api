package br.com.workmade.exceptions;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -5310311961324041391L;

	public RestauranteNaoEncontradoException(String msg) {
		super(msg);
	}

	public RestauranteNaoEncontradoException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}

