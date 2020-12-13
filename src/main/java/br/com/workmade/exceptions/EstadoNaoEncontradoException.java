package br.com.workmade.exceptions;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -5310311961324041391L;

	public EstadoNaoEncontradoException(String msg) {
		super(msg);
	}

	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Estado não encontrado com id: %d",estadoId));
	}
	public EstadoNaoEncontradoException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}

