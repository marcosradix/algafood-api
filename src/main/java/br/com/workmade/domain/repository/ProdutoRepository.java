package br.com.workmade.domain.repository;

import br.com.workmade.domain.model.Cidade;
import br.com.workmade.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
