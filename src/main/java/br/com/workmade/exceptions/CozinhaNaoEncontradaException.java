package br.com.workmade.exceptions;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -5310311961324041391L;

	public CozinhaNaoEncontradaException(String msg) {
		super(msg);
	}
	public CozinhaNaoEncontradaException(Long id) {
		this(String.format("Cozinha não encontrada com id: %d",id));
	}

	public CozinhaNaoEncontradaException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}

