package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.AbrigoDtoCadastro;
import br.com.alura.adopet.api.dto.pet.PetDtoCadastro;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    void cenario01() {
        //idade 1 anos e 3kg - ALTA
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
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }
}