package br.com.workmade.api.controller;

import br.com.workmade.domain.model.Cidade;
import br.com.workmade.infrastructure.service.impl.CidadeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cidade")
@Slf4j
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	public ResponseEntity<List<Cidade>> listar() {
		log.info("listando cidades..");
		return ResponseEntity.ok(this.cidadeService.listar());
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<Cidade> listarCidadeId(@PathVariable Long id) {
		log.info("listando cidade por id..");
		return ResponseEntity.ok(this.cidadeService.buscar(id));
	}

	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> apagarCidade(@PathVariable Long id) {
		log.info("apagando cidade por id..");
		this.cidadeService.remover(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
		log.info("salvando cidade..");
			return ResponseEntity.status(HttpStatus.CREATED).body(this.cidadeService.salvar(cidade));
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cidade> atualizar(@RequestBody Cidade cidade, @PathVariable Long id) {
		log.info("atualizando cidade..");
		cidade.setId(id);
		Cidade cidadeAtualizada = this.cidadeService.atualizar(cidade);
		return ResponseEntity.ok(cidadeAtualizada);
	}

}
