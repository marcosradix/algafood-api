package br.com.workmade.domain.model;

import br.com.workmade.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {

    @NotNull(groups = Groups.CozinhaId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "cozinha", orphanRemoval = false)
    private List<Restaurante> restaurantes;

    @Override
    public String toString() {
        return "Cozinha [id=" + id + ", nome=" + nome;
    }


}
