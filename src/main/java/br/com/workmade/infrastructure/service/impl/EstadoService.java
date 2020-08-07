package br.com.workmade.infrastructure.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Estado;
import br.com.workmade.domain.repository.EstadoRepository;
import br.com.workmade.infrastructure.service.IEstadoService;
@Service
public class EstadoService implements IEstadoService{

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado cozinha) {
		Optional<Estado> cozinhaToSave = Optional.of(cozinha);
		return this.estadoRepository.save(cozinhaToSave.get());
		
	}

	public List<Estado> listar() {
		return Optional.of(this.estadoRepository.findAll()).get();
	}
	
	

}
