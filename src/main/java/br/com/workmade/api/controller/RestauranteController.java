package br.com.workmade.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URI;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.workmade.domain.model.Cozinha;
import br.com.workmade.domain.model.Restaurante;
import br.com.workmade.infrastructure.service.impl.CozinhaService;
import br.com.workmade.infrastructure.service.impl.RestauranteService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "restaurante", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private CozinhaService cozinhaService;

	@GetMapping
	public ResponseEntity<List<Restaurante>> listar() {
		log.info("listando restaurantes..");
		return ResponseEntity.ok(this.restauranteService.listar());
	}

	@GetMapping("/top2-por-nome")
	public ResponseEntity<List<Restaurante>> listarTop2(String nome) {
		return ResponseEntity.ok(this.restauranteService.findTop2ByNomeContaining(nome));
	}

	@GetMapping("/count-por-cozinha")
	public ResponseEntity<Integer> countPorCozinha(Long cozinhaId) {
		return ResponseEntity.ok(this.restauranteService.countByCozinhaId(cozinhaId));
	}

	@GetMapping("/primeiro-por-nome")
	public ResponseEntity<Restaurante> listarPrimeiroNome(String nome) {
		return ResponseEntity.ok(this.restauranteService.findFirstRestauranteByNomeContaining(nome));
	}

	@GetMapping("/por-taxa-frete")
	public ResponseEntity<List<Restaurante>> listarPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		log.info("listando restaurantes por taxa frete..");
		return ResponseEntity.ok(this.restauranteService.findByTaxaFreteBetween(taxaInicial, taxaFinal));
	}

	@GetMapping("/por-nome-e-frete")
	public ResponseEntity<List<Restaurante>> listarPorTaxaNomeEFrete(String nome, BigDecimal taxaInicial,
			BigDecimal taxaFinal) {
		return ResponseEntity.ok(this.restauranteService.findCriteria(nome, taxaInicial, taxaFinal));
	}

	@GetMapping("/com-frete-gratis")
	public ResponseEntity<List<Restaurante>> listarPorFreteGratis(String nome) {
		return ResponseEntity.ok(this.restauranteService.findComFreteGratis(nome));
	}
	
	@GetMapping("/primeiro")
	public ResponseEntity<Restaurante> buscarPrimeiro() {
		return ResponseEntity.ok(this.restauranteService.buscarPrimeiro().get());
	}
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		log.info("listando restaurante por id..");
		return ResponseEntity.ok(this.restauranteService.buscar(id));
	}

	@GetMapping(value = "/por-nome")
	public ResponseEntity<List<Restaurante>> buscarPorNome(@RequestParam String nome, @RequestParam Long cozinhaId) {
		log.info("listando restaurante por id..");
		return ResponseEntity.ok(this.restauranteService.findByNomeContainingAndCozinhaId(nome, cozinhaId));
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
		
		if(restaurante.getCozinha().getId() == restauranteFound.getCozinha().getId()) {
			BeanUtils.copyProperties(restauranteFound.getCozinha(), restaurante.getCozinha());			
		}else {
			Cozinha cozinhaFound = cozinhaService.buscar(restaurante.getCozinha().getId());
			BeanUtils.copyProperties(cozinhaFound, restaurante.getCozinha());	
		}
		
		BeanUtils.copyProperties(restaurante, restauranteFound, "id", "formaPagamentos", "endereco", "dataCadastro", "produtos");
		Restaurante restauranteSalva = this.restauranteService.atualizar(restauranteFound);
		return ResponseEntity.ok().body(restauranteSalva);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Restaurante> atualizarParcial(@RequestBody Map<String, Object> campos,
														@PathVariable Long id, HttpServletRequest request) {
		log.info("atualizando restaurante..");
		Restaurante restauranteFound = this.restauranteService.buscar(id);

		mergeRestaurante(campos, restauranteFound, request);
		Restaurante restauranteSalvo = this.restauranteService.salvar(restauranteFound);
		return ResponseEntity.ok().body(restauranteSalvo);
	}

	private void mergeRestaurante(Map<String, Object> dadosOrigem, Restaurante restauranteDestino,HttpServletRequest request) {
		ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

			dadosOrigem.forEach((chave, valor) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, chave);
				field.setAccessible(true);
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		}catch (IllegalArgumentException e){
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			if (rootCause instanceof IgnoredPropertyException){
				throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);
			}
		}
	}
}
