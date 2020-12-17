package br.com.workmade.api.controller;

import br.com.workmade.domain.model.Cozinha;
import br.com.workmade.infrastructure.service.impl.CozinhaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.InetAddress;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "cozinha", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        log.info("deletando cozinha por id..");
        this.cozinhaService.remover(id);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Cozinha> salvar(@RequestBody @Valid Cozinha cozinha) {
        log.info("salvando cozinha..");
        String getRemoteHostName = InetAddress.getLoopbackAddress().getHostName();
        Cozinha cozinhaSalva = this.cozinhaService.salvar(cozinha);
        return ResponseEntity.created(URI.create(String.format("http://%s:8080/cozinha/listar/", getRemoteHostName)
                .concat(cozinhaSalva.getId().toString()))).body(cozinhaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@RequestBody @Valid Cozinha cozinha, @PathVariable Long id) {
        log.info("atualizando cozinha..");
        Cozinha cozinhaFound = this.cozinhaService.buscar(id);

        BeanUtils.copyProperties(cozinha, cozinhaFound, "id");
        Cozinha cozinhaSalva = this.cozinhaService.salvar(cozinhaFound);
        return ResponseEntity.ok().body(cozinhaSalva);
    }

    @GetMapping("/primeiro")
    public ResponseEntity<Cozinha> buscarPrimeiro() {
        return ResponseEntity.ok(this.cozinhaService.buscarPrimeiro());
    }
}
