package br.com.workmade.exceptions;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -5310311961324041391L;

	public ProdutoNaoEncontradoException(String msg) {
		super(msg);
	}

	public ProdutoNaoEncontradoException(Long cidadeId) {
		this(String.format("Produto não encontrado com id: %d",cidadeId));
	}

	public ProdutoNaoEncontradoException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}

