package br.com.workmade.infrastructure.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Cidade;
import br.com.workmade.domain.repository.CidadeRepository;
import br.com.workmade.infrastructure.service.ICidadeService;
@Service
public class CidadeService implements ICidadeService{

	@Autowired
	private CidadeRepository cidadeRepository;

	public Cidade salvar(Cidade cidade) {
		Optional<Cidade> cozinhaToSave = Optional.of(cidade);
		return this.cidadeRepository.save(cozinhaToSave.get());
	}
	


}
