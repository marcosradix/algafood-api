package br.com.workmade.infrastructure.repository.impl;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.com.workmade.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>  implements CustomJpaRepository<T, ID>{

	private EntityManager em;
	
	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}

	@Override
	public Optional<T> buscarPrimeiro() {
		var jpql = new StringBuilder(String.format("from %s", getDomainClass().getName())).toString();
		T entity = this.em.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();
		return Optional.ofNullable(entity);
	}
	

}
