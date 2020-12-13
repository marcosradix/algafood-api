package br.com.workmade.infrastructure.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

import br.com.workmade.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Cidade;
import br.com.workmade.domain.repository.CidadeRepository;
import br.com.workmade.infrastructure.service.ICidadeService;
@Service
public class CidadeService implements ICidadeService{

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoService estadoService;
	
	@Override
	public Cidade salvar(Cidade cidade) {
		Optional<Cidade> cozinhaToSave = Optional.of(cidade);
		try {
			estadoService.buscar(cidade.getEstado().getId());
			return this.cidadeRepository.save(cozinhaToSave.get());
		}catch (EstadoNaoEncontradoException e){
			throw new NegocioException(e.getMessage());
		}
	}

	@Override
	public List<Cidade> listar() {
		return this.cidadeRepository.findAll();
	}
	
	@Override
	public Cidade buscar(Long id){
		Optional<Cidade> cidadeFound = this.cidadeRepository.findById(id);
		return cidadeFound.orElseThrow( ()-> new CidadeNaoEncontradaException(id) );
	}

	@Override
	public void remover(Long id) {
		buscar(id);
		try{
			cidadeRepository.deleteById(id);
		}catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(String.format("A entidade de id: %d não pode ser removida antes de seu relacionamento", id));
		}

	}

	@Override
	public Cidade atualizar(Cidade cidade) {
		buscar(cidade.getId());
		Optional<Cidade> cidadeToSave = Optional.of(cidade);
		try {
			estadoService.buscar(cidade.getEstado().getId());
			return this.cidadeRepository.save(cidadeToSave.get());
		}catch (EstadoNaoEncontradoException e){
			throw new NegocioException(e.getMessage());
		}

	}


}
