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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidationTutorComLimiteDeAdocoesTest {
    @InjectMocks
    private ValidationTutorComLimiteDeAdocoes validation;

    @Mock
    private AdocaoRepository repository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("Exception lançada tutor com  5 adoções aprovadas")
    void cenario01() {
        //ARRANGE
        BDDMockito.given(repository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO))
                .willReturn(5);
        //ACT + ASSERT
        Assertions.assertThrows(ValidacaoExpection.class, () -> validation.validar(dto));
    }

    @Test
    @DisplayName("NÃO LANÇA EXCEPTION pois tutor tem menos de 5 adoções aprovadas")
    void cenario02() {
        //ARRANGE
        BDDMockito.given(repository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO))
                .willReturn(3);
        //ACT + ASSERT
        Assertions.assertDoesNotThrow(() -> validation.validar(dto));
    }
}