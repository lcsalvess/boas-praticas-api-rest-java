package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class ValidationTutorComAdocaoEmAndamentoTest {
    @InjectMocks
    private ValidationTutorComAdocaoEmAndamento validation;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("NÃO PERMITIR ADOÇÃO: tutor com outra solicitação")
    void cenario01() {
        //ARRANGE
        BDDMockito.given(adocaoRepository.existsByTutorIdAndStatus(
                dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(true);
        //ACT + ASSERT
        Assertions.assertThrows(ValidacaoExpection.class, () -> validation.validar(dto));
    }

    @Test
    @DisplayName("PERMITIR ADOÇÃO: tutor sem outra solicitação")
    void cenario02() {
        //ARRANGE
        BDDMockito.given(adocaoRepository.existsByTutorIdAndStatus(
                        dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(false);
        //ACT + ASSERT
        Assertions.assertDoesNotThrow(() -> validation.validar(dto));
    }
}