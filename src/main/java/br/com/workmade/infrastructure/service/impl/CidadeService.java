package br.com.workmade.infrastructure.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

import br.com.workmade.exceptions.CidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Cidade;
import br.com.workmade.domain.repository.CidadeRepository;
import br.com.workmade.exceptions.ObjectNotFoundException;
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
		return this.cidadeRepository.save(cozinhaToSave.get());
	}

	@Override
	public List<Cidade> listar() {
		return this.cidadeRepository.findAll();
	}
	
	@Override
	public Cidade buscar(Long id){
		Optional<Cidade> cidadeFound = this.cidadeRepository.findById(id);
		return cidadeFound.orElseThrow( ()-> new CidadeNaoEncontradaException("Não encontrado:".concat(""+id)) );
	}

	@Override
	public Cidade atualizar(Cidade cidade) {
		buscar(cidade.getId());
		Optional<Cidade> cidadeToSave = Optional.of(cidade);
		estadoService.buscar(cidade.getEstado().getId());
		return this.cidadeRepository.save(cidadeToSave.get());
	}


}
