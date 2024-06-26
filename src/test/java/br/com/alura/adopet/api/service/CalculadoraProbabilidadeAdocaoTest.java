package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.AbrigoDtoCadastro;
import br.com.alura.adopet.api.dto.pet.PetDtoCadastro;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    @DisplayName("Probabilidade alta para pet com peso e idade baixa")
    void probabilidadeCenario1() {
        //idade 1 anos e peso 3kg - ALTA
        //TRIPLE A (ARRANGE - ACT - ASSERT
        //ARRANGE
        Pet pet =
                new Pet(
                        new PetDtoCadastro(
                                TipoPet.CACHORRO,
                                "Jake",
                                "Spitz Alemao",
                                1,
                                "marrom",
                                3.0f
                        ),
                        new Abrigo(
                                new AbrigoDtoCadastro(
                                        "Abrigo Chico Xavier",
                                        "11999999999",
                                        "comercial@abrigocxavier.com"
                                )));
        var calculadora = new CalculadoraProbabilidadeAdocao();

        //ACT
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);
        //ASSERT
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }

    @Test
    @DisplayName("Probabilidade baixa para pet com peso alto e idade avançada")
    void probabilidadeCenario2() {
        //idade 11 anos e peso 16kg - BAIXA
        Pet pet =
                new Pet(
                        new PetDtoCadastro(
                                TipoPet.CACHORRO,
                                "THOR",
                                "SRD",
                                11,
                                "preto",
                                16.0f
                        ),
                        new Abrigo(
                                new AbrigoDtoCadastro(
                                        "Abrigo Chico Xavier",
                                        "11999999999",
                                        "comercial@abrigocxavier.com"
                                )));
        var calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA, probabilidade);
    }

    @Test
    @DisplayName("Probabilidade média para pet com peso baixo e idade avançada")
    void probabilidadeCenario3 () {
        //idade 11 anos e peso 4.5kg - MEDIA
        Pet pet =
                new Pet(
                        new PetDtoCadastro(
                                TipoPet.GATO,
                                "PANDORA",
                                "SRD",
                                11,
                                "preto",
                                4.5f
                        ),
                        new Abrigo(
                                new AbrigoDtoCadastro(
                                        "Abrigo Chico Xavier",
                                        "11999999999",
                                        "comercial@abrigocxavier.com"
                                )));
        var calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }
}