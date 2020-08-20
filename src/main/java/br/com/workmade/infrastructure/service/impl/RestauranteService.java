package br.com.workmade.infrastructure.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Restaurante;
import br.com.workmade.domain.repository.RestauranteRepository;
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
	


}
