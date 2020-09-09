package br.com.workmade.api.controller;

import java.net.InetAddress;
import java.net.URI;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<List<Cozinha>> listar() {
		log.info("listando cozinhas..");
		return ResponseEntity.ok(this.cozinhaService.listar());
	}
	
	@GetMapping("/exists")
	public ResponseEntity<Boolean> existsPorNome(String nome) {
		return ResponseEntity.ok(this.cozinhaService.existsByNome(nome));
	}
	
	@GetMapping("/listar/por-nome")
	public ResponseEntity<List<Cozinha>> listarPorNome(String nome) {
		log.info("listando cozinhas..");
		return ResponseEntity.ok(this.cozinhaService.findTodasByNomeContaining(nome));
	}

	
	@GetMapping(value = "/listar/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		log.info("listando cozinha por id..");
		return ResponseEntity.ok(this.cozinhaService.buscar(id));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		log.info("deletando cozinha por id..");
		Cozinha cozinhaFound = this.cozinhaService.buscar(id);
		this.cozinhaService.remover(cozinhaFound);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/salvar")
	public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
		log.info("salvando cozinha..");
		String getRemoteHostName = InetAddress.getLoopbackAddress().getHostName();
		Cozinha cozinhaSalva = this.cozinhaService.salvar(cozinha);
		return ResponseEntity.created(URI.create(String.format("http://%s:8080/cozinha/listar/", getRemoteHostName)
				.concat(cozinhaSalva.getId().toString()))).body(cozinhaSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha, @PathVariable Long id) {
		log.info("atualizando cozinha..");
		Cozinha cozinhaFound = this.cozinhaService.buscar(id);
		
		BeanUtils.copyProperties(cozinha, cozinhaFound, "id");
		Cozinha cozinhaSalva = this.cozinhaService.salvar(cozinhaFound);
		return ResponseEntity.ok().body(cozinhaSalva);
	}
}
