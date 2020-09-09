package br.com.workmade.infrastructure.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Restaurante;
import br.com.workmade.domain.repository.RestauranteRepository;
import br.com.workmade.exceptions.ObjectNotFoundException;
import br.com.workmade.infrastructure.service.IRestauranteService;
@Service
public class RestauranteService implements IRestauranteService{

	@Autowired
	private RestauranteRepository restauranteRepository;

	public Restaurante salvar(Restaurante restaurante) {
		Optional<Restaurante> cozinhaToSave = Optional.of(restaurante);
		return this.restauranteRepository.save(cozinhaToSave.get());
	}

	@Override
	public List<Restaurante> listar() {
		return this.restauranteRepository.findAll();
	}

	@Override
	public Restaurante buscar(Long id) {
		return this.restauranteRepository.findById(id).get();
	}

	@Override
	public void remover(Restaurante restaurante) {
		this.restauranteRepository.delete(restaurante);
		
	}

	@Override
	public List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return this.restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@Override
	public List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId) {
		return this.restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
	}

	@Override
	public Restaurante findFirstRestauranteByNomeContaining(String nome) {
		return this.restauranteRepository.findFirstRestauranteByNomeContaining(nome)
				.orElseThrow(()-> new ObjectNotFoundException(String.format("nada encontrado por %s", nome) ));
	}

	@Override
	public List<Restaurante> findTop2ByNomeContaining(String nome) {
		return this.restauranteRepository.findTop2ByNomeContaining(nome);
	}

	@Override
	public int countByCozinhaId(Long cozinhaId) {
		return this.restauranteRepository.countByCozinhaId(cozinhaId);
	}


}
