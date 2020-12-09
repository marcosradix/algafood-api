package br.com.workmade.infrastructure.service;

import br.com.workmade.domain.model.Cidade;
import br.com.workmade.domain.model.Produto;

import java.util.List;

public interface IProdutoService {
	
	Produto salvar(Produto produto);

	Produto atualizar(Produto produto);
	
	List<Produto> listar();

	Produto buscar(Long id);

}
