package br.com.workmade.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public List<Estado> listar() {
		log.info("listando estados..");
		return this.estadoService.listar();
	}

	@PostMapping
	public Estado salvar(@RequestBody Estado estado) {
		log.info("salvando estado..");
		return this.estadoService.salvar(estado);
	}
	
	@PutMapping
	public Estado atualizar(@RequestBody Estado estado) {
		log.info("atualizando cidade..");
		return this.estadoService.atualizar(estado);
	}
}
