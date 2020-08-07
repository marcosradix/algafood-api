package br.com.workmade.infrastructure.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Grupo;
import br.com.workmade.domain.repository.GrupoRepository;
import br.com.workmade.infrastructure.service.IGrupoService;
@Service
public class GrupoService implements IGrupoService{

	@Autowired
	private GrupoRepository grupoRepository;
	
	public Grupo salvar(Grupo grupo) {
		Optional<Grupo> grupoToSave = Optional.of(grupo);
		return this.grupoRepository.save(grupoToSave.get());
		
	}

}
