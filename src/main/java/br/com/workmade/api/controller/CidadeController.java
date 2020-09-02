package br.com.workmade.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.workmade.domain.model.Cidade;
import br.com.workmade.infrastructure.service.impl.CidadeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("cidade")
@Slf4j
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	public List<Cidade> listar() {
		log.info("listando cidades..");
		return this.cidadeService.listar();
	}

	@PostMapping
	public Cidade salvar(@RequestBody Cidade cidade) {
		log.info("salvando cidade..");
		return this.cidadeService.salvar(cidade);
	}
	@PutMapping
	public Cidade atualizar(@RequestBody Cidade cidade) {
		log.info("atualizando cidade..");
		return this.cidadeService.atualizar(cidade);
	}
	
}
