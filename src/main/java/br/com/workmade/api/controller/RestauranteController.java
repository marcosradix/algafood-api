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

import br.com.workmade.domain.model.Restaurante;
import br.com.workmade.infrastructure.service.impl.RestauranteService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="restaurante", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;


	@GetMapping
	public List<Restaurante> listar() {
		log.info("listando restaurantes..");
		return this.restauranteService.listar();
	}
		
	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		log.info("listando restaurante por id..");
		return ResponseEntity.ok(this.restauranteService.buscar(id));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Restaurante> remover(@PathVariable Long id) {
		log.info("deletando restaurante por id..");
		Restaurante restauranteFound = this.restauranteService.buscar(id);
		this.restauranteService.remover(restauranteFound);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restaurante) {
		log.info("salvando restaurante..");
		String getRemoteHostName = InetAddress.getLoopbackAddress().getHostName();
		Restaurante restauranteSalvo = this.restauranteService.salvar(restaurante);
		return ResponseEntity.created(URI.create(String.format("http://%s:8080/restaurante/listar/", getRemoteHostName)
				.concat(restauranteSalvo.getId().toString()))).body(restauranteSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Restaurante> atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {
		log.info("atualizando restaurante..");
		Restaurante restauranteFound = this.restauranteService.buscar(id);
		
		BeanUtils.copyProperties(restaurante, restauranteFound, "id");
		Restaurante restauranteSalva = this.restauranteService.salvar(restauranteFound);
		return ResponseEntity.ok().body(restauranteSalva);
	}
}
