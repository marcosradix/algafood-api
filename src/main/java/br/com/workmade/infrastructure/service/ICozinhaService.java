package br.com.workmade.infrastructure.service;

import java.util.List;
import java.util.Optional;

import br.com.workmade.domain.model.Cozinha;

public interface ICozinhaService {
	
	Cozinha salvar(Cozinha cozinha);
	
	List<Cozinha> listar();
	
	Cozinha buscar(Long id);
	
	void remover(Cozinha cozinha);
	

	List<Cozinha> findTodasByNomeContaining(String nome);
	
	Optional<Cozinha> findByNome(String nome);
	
	boolean existsByNome(String nome);

}
