package br.com.workmade.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.workmade.domain.model.Cozinha;
import br.com.workmade.domain.repository.CozinhaRepository;
import br.com.workmade.utils.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class TesteCozinhaIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository repo;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		databaseCleaner.clearTables();
		prepararDados();
		
	}
	
	@Test
	public void deve_retornar_status_200_quando_consultar_cozinhas() {
		 given()
		.basePath("/cozinha/listar")
		.port(port)
		.accept(ContentType.JSON)
		.when().get().then().statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deve_retornar_2_cozinhas_quando_consultar_cozinhas() {
		 given()
			.basePath("/cozinha/listar")
			.port(port)
			.accept(ContentType.JSON)
		.when().get().then()
		.body("", Matchers.hasSize(2))
		.body("nome", Matchers.hasItems("Americana"));
		
	}
	
	@Test
	public void deve_retornar_status_201_quando_cadastrar_cozinha() {
		var headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		given().body("{\"nome\":\"Chinesa\"}")
		.headers(headers)
		.accept(ContentType.JSON)
		.when().post("/cozinha/salvar").then()
		.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deve_retornar_status_correto_quando_consultar_cozinha() {
		 given()
		 .pathParam("cozinhaID", 2)
			.basePath("/cozinha/listar")
			.port(port)
			.accept(ContentType.JSON)
		.when().get("/{cozinhaID}").then()
		  .statusCode(HttpStatus.OK.value())
		  .body("nome", equalTo("Americana"));
	}
	
	@Test
	public void deve_retornar_status_404_quando_consultar_cozinha_inexistente() {
		 given()
		 .pathParam("cozinhaID", 12)
			.basePath("/cozinha/listar")
			.port(port)
			.accept(ContentType.JSON)
		.when().get("/{cozinhaID}").then()
		  .statusCode(HttpStatus.NOT_FOUND.value());
		
	}
	
	
	private void prepararDados() {
		Cozinha c1 = new Cozinha();
		c1.setNome("Tailandesa");
		Cozinha c2 = new Cozinha();
		c2.setNome("Americana");
		repo.saveAll(List.of(c1,c2));
		
	}
}















