package br.com.workmade.infrastructure.service;

import java.util.List;

import br.com.workmade.domain.model.Restaurante;

public interface IRestauranteService {
	
	Restaurante salvar(Restaurante restaurante);
	
	List<Restaurante> listar();
	
	Restaurante buscar(Long id);
	
	void remover(Restaurante restaurante);

}
