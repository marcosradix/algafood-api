package br.com.workmade.infrastructure.service;

import java.util.List;

import br.com.workmade.domain.model.Cozinha;

public interface ICozinhaService {
	
	Cozinha salvar(Cozinha cozinha);
	
	List<Cozinha> listar();
	
	Cozinha buscar(Long id);

}
