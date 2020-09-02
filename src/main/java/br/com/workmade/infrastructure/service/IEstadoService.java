package br.com.workmade.infrastructure.service;

import java.util.List;

import br.com.workmade.domain.model.Estado;

public interface IEstadoService {
	
	Estado salvar(Estado estado);
	
	List<Estado> listar();
	
	Estado atualizar(Estado estado);
	
	Estado buscar(Long id);

}
