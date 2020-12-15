package br.com.workmade.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name="taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@JsonIgnoreProperties({"hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@Column(nullable = false, columnDefinition = "datetime")
	@CreationTimestamp
	private LocalDate dataCadastro;
	
	@JsonIgnore
	@Column(nullable = false, columnDefinition = "datetime")
	@UpdateTimestamp
	private LocalDate dataAtualizacao;

	@JsonIgnore
	  @ManyToMany
	    @JoinTable(name="restaurante_forma_pagamento", joinColumns=
	    {@JoinColumn(name="restaurante_id")}, inverseJoinColumns=
	      {@JoinColumn(name="pagamento_id")})
	private List<FormaPagamento> formaPagamentos;
	  
	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
	private List<Produto> produtos;

}
