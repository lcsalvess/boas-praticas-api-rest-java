package br.com.alura.adopet.api.validations.adocao;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.validations.ValidationTutorComAdocaoEmAndamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ValidationPetOutraSolicitacaoTest {
    @InjectMocks
    ValidationTutorComAdocaoEmAndamento validation;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private SolicitacaoAdocaoDto dto;
    @Test
    @DisplayName("NÃO PERMITIR solicitação de adoção com pet com outro pedido de adoção")
    void cenario1() {
        //ARRANGE
        BDDMockito.given(adocaoRepository
                .existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(true);
        //ACT + ASSERT
        Assertions.assertThrows(ValidacaoExpection.class, () -> validation.validar(dto));
    }

    @Test
    @DisplayName("PERMITIR solicitação de adoção")
    void cenario2() {
        //ARRANGE
        BDDMockito.given(adocaoRepository
                        .existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(false);
        //ACT + ASSERT
        Assertions.assertThrows(ValidacaoExpection.class, () -> validation.validar(dto));
    }
}