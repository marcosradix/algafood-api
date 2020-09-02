package br.com.workmade.infrastructure.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Estado;
import br.com.workmade.domain.repository.EstadoRepository;
import br.com.workmade.exceptions.ObjectNotFoundException;
import br.com.workmade.infrastructure.service.IEstadoService;
@Service
public class EstadoService implements IEstadoService{

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		Optional<Estado> estadoToSave = Optional.of(estado);
		return this.estadoRepository.save(estadoToSave.get());
		
	}

	public List<Estado> listar() {
		return Optional.of(this.estadoRepository.findAll()).get();
	}
	
	@Override
	public Estado buscar(Long id){
		Optional<Estado> estadoFound = this.estadoRepository.findById(id);
		return estadoFound.orElseThrow( ()-> new ObjectNotFoundException("Não encontrado:".concat(""+id)) );
	}

	@Override
	public Estado atualizar(Estado estado) {
		buscar(estado.getId());
		Optional<Estado> estadoToSave = Optional.of(estado);
		return this.estadoRepository.save(estadoToSave.get());
	}


}
