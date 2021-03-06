package br.com.workmade.infrastructure.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.com.workmade.domain.model.Cozinha;
import br.com.workmade.domain.repository.CozinhaRepository;
import br.com.workmade.exceptions.RestauranteNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Restaurante;
import br.com.workmade.domain.repository.RestauranteRepository;
import br.com.workmade.exceptions.ObjectNotFoundException;
import br.com.workmade.infrastructure.service.IRestauranteService;
@Service
public class RestauranteService implements IRestauranteService{

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).get();
		restaurante.setCozinha(cozinha);
		return this.restauranteRepository.save(restaurante);
	}

	public Restaurante atualizar(Restaurante restaurante) {
		buscar(restaurante.getId());
		return this.restauranteRepository.save(restaurante);
	}

	@Override
	public List<Restaurante> listar() {
		return this.restauranteRepository.findAll();
	}

	@Override
	public Restaurante buscar(Long id) {
		return this.restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException("restaurante não encontrado: "+id));
	}

	@Override
	public void remover(Restaurante restaurante) {
		buscar(restaurante.getId());
		this.restauranteRepository.delete(restaurante);
		
	}

	@Override
	public List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return this.restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@Override
	public List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId) {
		return this.restauranteRepository.consultarPorNome(nome, cozinhaId);
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

	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return this.restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}

	@Override
	public List<Restaurante> findCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return this.restauranteRepository.findCriteria(nome, taxaFreteInicial, taxaFreteFinal);
	}

	@Override
	public List<Restaurante> findAll(Specification<Restaurante> especifications) {
		return this.restauranteRepository.findAll(especifications);
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return this.restauranteRepository.findComFreteGratis(nome);
	}

	@Override
	public Optional<Restaurante> buscarPrimeiro() {
		return this.restauranteRepository.buscarPrimeiro();
	}

}
