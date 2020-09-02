package br.com.workmade.infrastructure.service.impl;

import java.util.List;
import java.util.Optional;

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
		return cidadeFound.orElseThrow( ()-> new ObjectNotFoundException("Não encontrado:".concat(""+id)) );
	}

	@Override
	public Cidade atualizar(Cidade cidade) {
		buscar(cidade.getId());
		Optional<Cidade> cozinhaToSave = Optional.of(cidade);
		return this.cidadeRepository.save(cozinhaToSave.get());
	}


}
