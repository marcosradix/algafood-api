package br.com.workmade.infrastructure.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Cozinha;
import br.com.workmade.domain.repository.CozinhaRepository;
import br.com.workmade.exceptions.ObjectNotFoundException;
import br.com.workmade.infrastructure.service.ICozinhaService;
@Service
public class CozinhaService implements ICozinhaService{

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		Optional<Cozinha> cozinhaToSave = Optional.of(cozinha);
		return this.cozinhaRepository.save(cozinhaToSave.get());
		
	}

	public List<Cozinha> listar() {
		return Optional.of(this.cozinhaRepository.findAll()).orElseGet(()-> new ArrayList< Cozinha>());
		
	}

	public Cozinha buscar(Long id){
		Optional<Cozinha> cozinhaFound = this.cozinhaRepository.findById(id);
		return cozinhaFound.orElseThrow( ()-> new ObjectNotFoundException("Não encontrado:".concat(""+id)) );
	}

	@Override
	public void remover(Cozinha cozinha) {
		this.cozinhaRepository.delete(cozinha);
		
	}

}
