package br.com.workmade.infrastructure.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.domain.model.FormaPagamento;
import br.com.workmade.domain.repository.FormaPagamentoRepository;
import br.com.workmade.infrastructure.service.IFormaPagamentoService;
@Service
public class FormaPagamentoService implements IFormaPagamentoService{

	@Autowired
	private FormaPagamentoRepository estadoRepository;
	
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		Optional<FormaPagamento> formaPagamentoToSave = Optional.of(formaPagamento);
		return this.estadoRepository.save(formaPagamentoToSave.get());
		
	}

}
