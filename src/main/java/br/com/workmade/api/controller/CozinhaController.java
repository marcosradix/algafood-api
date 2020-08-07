package br.com.workmade.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.workmade.domain.model.Cozinha;
import br.com.workmade.infrastructure.service.impl.CozinhaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="cozinha", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CozinhaController {

	@Autowired
	private CozinhaService cozinhaService;

	@GetMapping("/listar")
	public List<Cozinha> listar() {
		log.info("listando cozinhas..");
		return this.cozinhaService.listar();
	}
	
	@GetMapping(value = "/listar/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		log.info("listando cozinha por id..");
		return   ResponseEntity.ok(this.cozinhaService.buscar(id));
	}

	@PostMapping("/salvar")
	public Cozinha salvar(@RequestBody Cozinha cozinha) {
		log.info("salvando cozinha..");
		return this.cozinhaService.salvar(cozinha);
	}
}
