package br.com.workmade.exceptions;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -5310311961324041391L;

	public NegocioException(String msg) {
		super(msg);
	}

	public NegocioException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}

