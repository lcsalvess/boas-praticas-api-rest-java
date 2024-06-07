package br.com.alura.adopet.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdocaoControllerIntegrationTest {
    // com essa abordagem, são disparadas requisições
    // reais para a API, utilizando a classe TestRestTemplate do Spring
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Código 400 para solicitação COM ERROS")
    void cenario01() {
        //ARRANGE
        String json = "{}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //ACT
        ResponseEntity<Void> response = restTemplate.exchange(
                "/adocoes",
                HttpMethod.POST,
                new HttpEntity<>(json, headers),
                Void.class
        );
        //ASSERT
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    @DisplayName("Código 200 para solicitação SEM ERROS")
    void cenario02() {
        // ARRANGE
        String json = """
                {
                    "idPet": 1,
                    "idTutor": 1,
                    "motivo": "Motivo qualquer"
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // ACT
        ResponseEntity<Void> response = restTemplate.exchange(
                "/adocoes",
                HttpMethod.POST,
                new HttpEntity<>(json, headers),
                Void.class
        );

        // ASSERT
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
