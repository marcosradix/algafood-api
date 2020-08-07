package br.com.workmade.infrastructure.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.Permissao;
import br.com.workmade.domain.repository.PermissaoRepository;
import br.com.workmade.infrastructure.service.IPermissaoService;
@Service
public class PermissaoService implements IPermissaoService{

	@Autowired
	private PermissaoRepository permissaoRepository;

	public Permissao salvar(Permissao permissao) {
		Optional<Permissao> cozinhaToSave = Optional.of(permissao);
		return this.permissaoRepository.save(cozinhaToSave.get());
	}
	


}
