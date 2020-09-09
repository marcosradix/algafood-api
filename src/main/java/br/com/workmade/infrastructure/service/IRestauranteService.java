package br.com.workmade.infrastructure.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.workmade.domain.model.Restaurante;

public interface IRestauranteService {
	
	Restaurante salvar(Restaurante restaurante);
	
	List<Restaurante> listar();
	
	Restaurante buscar(Long id);
	
	void remover(Restaurante restaurante);
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	Restaurante findFirstRestauranteByNomeContaining(String nome);
	
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long cozinhaId);

}
