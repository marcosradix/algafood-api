package br.com.workmade.infrastructure.service;

import java.util.List;

import br.com.workmade.domain.model.Cidade;

public interface ICidadeService {
	
	Cidade salvar(Cidade cidade);
	
	List<Cidade> listar();

}
