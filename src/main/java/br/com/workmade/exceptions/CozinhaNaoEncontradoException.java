package br.com.workmade.exceptions;

public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -5310311961324041391L;

	public CozinhaNaoEncontradoException(String msg) {
		super(msg);
	}
	public CozinhaNaoEncontradoException(Long id) {
		this(String.format("Cozinha não encontrada com id: %d",id));
	}

	public CozinhaNaoEncontradoException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}

