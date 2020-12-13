package br.com.workmade.infrastructure.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.workmade.exceptions.CozinhaNaoEncontradoException;
import br.com.workmade.exceptions.EntidadeEmUsoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Cozinha;
import br.com.workmade.domain.repository.CozinhaRepository;
import br.com.workmade.exceptions.ObjectNotFoundException;
import br.com.workmade.infrastructure.service.ICozinhaService;
@Service
public class CozinhaService implements ICozinhaService{

	@Autowired
	private CozinhaRepository cozinhaRepository;
	@Autowired
	private RestauranteService restauranteService;

	@Override
	public Cozinha salvar(Cozinha cozinha) {
		Optional<Cozinha> cozinhaToSave = Optional.of(cozinha);
		return this.cozinhaRepository.save(cozinhaToSave.get());
		
	}
	@Override
	public List<Cozinha> listar() {
		return Optional.of(this.cozinhaRepository.findAll()).orElseGet(()-> new ArrayList< Cozinha>());
		
	}
	@Override
	public Cozinha buscar(Long id){
		Optional<Cozinha> cozinhaFound = this.cozinhaRepository.findById(id);
		return cozinhaFound.orElseThrow( ()-> new CozinhaNaoEncontradoException(id) );
	}

	@Override
	public void remover(Long cozinhaId) {
		Cozinha cozinhaEncontrada = buscar(cozinhaId);
		try {
			this.cozinhaRepository.delete(cozinhaEncontrada);
		}catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException("existe um relacionamento e por isso não pode apagar");
		}

		
	}
	@Override
	public List<Cozinha> findTodasByNomeContaining(String nome) {
		return this.cozinhaRepository.findTodasByNomeContaining(nome);
	}
	@Override
	public Optional<Cozinha> findByNome(String nome) {
		return this.cozinhaRepository.findByNome(nome);
	}
	@Override
	public boolean existsByNome(String nome) {
		return this.cozinhaRepository.existsByNome(nome);
	}
	public Cozinha buscarPrimeiro() {
		return this.cozinhaRepository.buscarPrimeiro().get();
	}
	
}
