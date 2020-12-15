package br.com.workmade.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @CreationTimestamp
    private LocalDate dataCadastro;


    @ManyToMany
    @JoinTable(name = "usuario_grupo", joinColumns =
            {@JoinColumn(name = "usuario_id")}, inverseJoinColumns = {@JoinColumn(name = "grupo_id")})
    private List<Grupo> grupos;
}
