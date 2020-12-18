package br.com.workmade.domain.model;

import br.com.workmade.core.validation.Groups;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
    @ManyToOne
    @JoinColumn(name = "estado_id",nullable = false)
    private Estado estado;

}
