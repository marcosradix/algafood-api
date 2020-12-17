package br.com.workmade.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import br.com.workmade.Groups;
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

    @NotBlank(message = "O nome do restaurante deve ser informado")
    @Column(nullable = false)
    private String nome;

    @PositiveOrZero
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    //@JsonIgnoreProperties({"hibernateLazyInitializer"})
    //@ManyToOne(fetch = FetchType.LAZY)
    @Valid
    @ConvertGroup(from = Default.class, to= Groups.CozinhaId.class)
    @NotNull(message = "Você deve informar o id da cozinha")
    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
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
    @JoinTable(name = "restaurante_forma_pagamento", joinColumns =
            {@JoinColumn(name = "restaurante_id")}, inverseJoinColumns =
            {@JoinColumn(name = "pagamento_id")})
    private List<FormaPagamento> formaPagamentos;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
    private List<Produto> produtos;

}
