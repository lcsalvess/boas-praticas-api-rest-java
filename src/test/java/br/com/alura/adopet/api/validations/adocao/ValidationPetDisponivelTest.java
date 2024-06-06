package br.com.alura.adopet.api.validations.adocao;


import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidationPetDisponivelTest {
    @InjectMocks
    ValidationPetDisponivel validation;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("Permite realizar solicitação de Adoção")
    void realizarCenario1() {
        BDDMockito
                .given(petRepository
                        .getReferenceById(dto.idPet()))
                .willReturn(pet);
        BDDMockito
                .given(pet.getAdotado())
                .willReturn(false);

        Assertions.assertDoesNotThrow(() -> validation.validar(dto));
    }

    @Test
    @DisplayName("Lança excessão ao realizar solicitação de Adoção")
    void realizarCenario2() {
        BDDMockito
                .given(petRepository
                        .getReferenceById(dto.idPet()))
                .willReturn(pet);
        BDDMockito
                .given(pet.getAdotado())
                .willReturn(true);
        Assertions.assertThrows(RuntimeException.class, () -> validation.validar(dto));

    }
}