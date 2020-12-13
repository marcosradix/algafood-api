package br.com.workmade.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.workmade.domain.model.Estado;
import br.com.workmade.infrastructure.service.impl.EstadoService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("estado")
@Slf4j
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	@GetMapping
	public ResponseEntity<List<Estado>> listar() {
		log.info("listando estados..");
		return ResponseEntity.ok(this.estadoService.listar());
	}

	@PostMapping
	public ResponseEntity<Estado> salvar(@RequestBody Estado estado) {
		log.info("salvando estado..");
		return ResponseEntity.status(HttpStatus.CREATED).body(this.estadoService.salvar(estado));
	}
	
	@PutMapping
	public ResponseEntity<Estado> atualizar(@RequestBody Estado estado) {
		log.info("atualizando cidade..");
		return ResponseEntity.ok(this.estadoService.atualizar(estado));
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void>apagar(@PathVariable Long id){
		 estadoService.apagar(id);
		return ResponseEntity.noContent().build();
	}
}
