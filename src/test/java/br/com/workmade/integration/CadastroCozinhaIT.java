package br.com.workmade.integration;

import br.com.workmade.domain.model.Cozinha;
import br.com.workmade.exceptions.CozinhaNaoEncontradaException;
import br.com.workmade.exceptions.EntidadeEmUsoException;
import br.com.workmade.infrastructure.service.impl.CozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIT {

	@Autowired
	private CozinhaService cozinhaService;

	@Test
	public void deve_cadastrar_cozinha_com_sucesso() {
		//cenário
		var cozinha = new Cozinha();
		cozinha.setNome("Chinesa");

		//ação
		Cozinha cozinhaSalva = cozinhaService.salvar(cozinha);

		//validação
		assertThat(cozinhaSalva).isNotNull();
		assertThat(cozinhaSalva.getId()).isNotNull();

	}
	@Test(expected = ConstraintViolationException.class)
	public void deve_falhar_quando_cadastrar_cozinha_sem_nome(){
		var cozinha = new Cozinha();
		cozinha.setNome(null);
		Cozinha cozinhaSalva = cozinhaService.salvar(cozinha);

	}
	@Test(expected = EntidadeEmUsoException.class)
	public void deve_falhar_quando_excluir_cozinha_em_uso(){
		var cozinha = new Cozinha();
		cozinha.setNome("Thai Delivery");
		cozinha.setId(2L);
		cozinhaService.remover(cozinha.getId());
	}

	@Test(expected = CozinhaNaoEncontradaException.class)
	public void deve_falhar_quando_excluir_cozinha_inexistente(){
		var cozinha = new Cozinha();
		cozinha.setNome("Thai Delivery");
		cozinha.setId(21L);
		cozinhaService.remover(cozinha.getId());
	}

}
