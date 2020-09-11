package br.com.workmade.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.com.workmade.domain.model.Restaurante;

public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		final var JPQL = new StringBuilder();
		var parametros = new HashMap<String, Object>();

		JPQL.append("from Restaurante where 0 = 0 ");
		if (StringUtils.hasLength(nome)) {
			JPQL.append("and nome like :nome ");
			parametros.put("nome", "%".concat(nome).concat("%"));
		}

		if (taxaFreteInicial != null) {
			JPQL.append("and taxaFrete >= :taxaFreteInicial ");
			parametros.put("taxaFreteInicial", taxaFreteInicial);
		}

		if (taxaFreteFinal != null) {
			JPQL.append("and taxaFrete <=  :taxaFreteFinal ");
			parametros.put("taxaFreteFinal", taxaFreteFinal);
		}
		TypedQuery<Restaurante> query = manager.createQuery(JPQL.toString(), Restaurante.class);

		parametros.forEach((chave, valor) -> {
			query.setParameter(chave, valor);
		});
		return query.getResultList();
	}
	@Override
	public List<Restaurante> findCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(nome)) {
			Predicate nomePredicate = builder.like(root.get("nome"), "%".concat(nome).concat("%"));
			predicates.add(nomePredicate);
		}
		
		if(taxaFreteInicial != null ) {
			Predicate taxaInicialPredicate = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
			predicates.add(taxaInicialPredicate);
		}
		
		if( taxaFreteFinal != null ) {
			Predicate taxaFinalPredicate = builder.lessThanOrEqualTo(root.get("taxaFrete"),taxaFreteFinal);
			predicates.add(taxaFinalPredicate);
		}
		
			criteria.where(predicates.toArray(new Predicate[0]));
		
		
		TypedQuery<Restaurante> query =  manager.createQuery(criteria);
		return query.getResultList();
	}

}





