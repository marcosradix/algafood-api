package br.com.workmade.infrastructure.service.impl;

import br.com.workmade.domain.model.Cidade;
import br.com.workmade.domain.model.Produto;
import br.com.workmade.domain.repository.CidadeRepository;
import br.com.workmade.domain.repository.ProdutoRepository;
import br.com.workmade.exceptions.ObjectNotFoundException;
import br.com.workmade.infrastructure.service.ICidadeService;
import br.com.workmade.infrastructure.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements IProdutoService{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public Produto salvar(Produto produto) {
		Optional<Produto> produtoToSave = Optional.of(produto);
		return this.produtoRepository.save(produtoToSave.get());
	}

	@Override
	public List<Produto> listar() {
		return this.produtoRepository.findAll();
	}
	
	@Override
	public Produto buscar(Long id){
		Optional<Produto> produtoFound = this.produtoRepository.findById(id);
		return produtoFound.orElseThrow( ()-> new ObjectNotFoundException("Não encontrado:".concat(""+id)) );
	}

	@Override
	public Produto atualizar(Produto produto) {
		buscar(produto.getId());
		Optional<Produto> produtoToSave = Optional.of(produto);
		return this.produtoRepository.save(produtoToSave.get());
	}


}
