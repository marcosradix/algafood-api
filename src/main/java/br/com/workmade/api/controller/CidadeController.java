package br.com.workmade.api.controller;

import br.com.workmade.domain.model.Cidade;
import br.com.workmade.exceptions.EntidadeNaoEncontradaException;
import br.com.workmade.exceptions.EstadoNaoEncontradoException;
import br.com.workmade.exceptions.NegocioException;
import br.com.workmade.exceptions.ObjectNotFoundException;
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

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
		log.info("salvando cidade..");
		try {
			return ResponseEntity.ok(this.cidadeService.salvar(cidade));
		}catch (EstadoNaoEncontradoException e){
			throw new NegocioException(e.getMessage());
		}
	}
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Cidade> atualizar(@RequestBody Cidade cidade, @PathVariable Long id) {
		log.info("atualizando cidade..");
		cidade.setId(id);
		Cidade cidadeEncontrada = cidadeService.buscar(id);
		BeanUtils.copyProperties(cidade, cidadeEncontrada, "id");
		try {
			return ResponseEntity.ok(this.cidadeService.atualizar(cidadeEncontrada));
		}catch (EstadoNaoEncontradoException e){
			throw new NegocioException(e.getMessage());
		}
	}
	
}
