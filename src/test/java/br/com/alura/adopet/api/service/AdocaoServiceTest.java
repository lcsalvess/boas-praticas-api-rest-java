package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.email.EmailService;
import br.com.alura.adopet.api.validations.ValidationSolicitacaoAdocao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {
    @InjectMocks
    private AdocaoService adocaoService;
    @Spy
    private List<ValidationSolicitacaoAdocao> validation = new ArrayList<>();
    @Mock
    private ValidationSolicitacaoAdocao validador1;
    @Mock
    private ValidationSolicitacaoAdocao validador2;
    @Mock
    private AdocaoRepository repository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private EmailService emailService;
    @Mock
    private Pet pet;
    @Mock
    private Tutor tutor;
    @Mock
    private Adocao adocao;
    @Mock
    private Abrigo abrigo;
    private SolicitacaoAdocaoDto dto;
    @Captor
    private ArgumentCaptor<Adocao> adocaoCaptor;

    @Test
    void deveriaSalvarAdocaoAoSolicitar(){
        //ARRANGE
        this.dto = new SolicitacaoAdocaoDto(10L, 20L, "motivo qualquer");
        BDDMockito
                .given(petRepository
                        .getReferenceById(dto.idPet()))
                .willReturn(pet);
        BDDMockito
                .given(tutorRepository
                        .getReferenceById(dto.idTutor()))
                .willReturn(tutor);
        BDDMockito
                .given(pet.getAbrigo())
                .willReturn(abrigo);

        //ACT
        adocaoService.solicitar(dto);
        //ASSERT
        then(repository).should().save(adocaoCaptor.capture());
        Adocao adocaoSalva = adocaoCaptor.getValue();
        assertEquals(pet, adocaoSalva.getPet());
        assertEquals(tutor, adocaoSalva.getTutor());
        assertEquals(dto.motivo(), adocaoSalva.getMotivo());
    }

    @Test
    void deveriaChamarValidadoresDeAdocaoAoSolicitar(){
        //ARRANGE
        this.dto = new SolicitacaoAdocaoDto(10L, 20L, "motivo qualquer");
        // Behavior-Driven-Deelopment(BDD)
        // Quando o método for chamado com o valor retornado pelo dto,
        // o Mockito irá retornar o objeto pet.
        BDDMockito
                .given(petRepository
                        .getReferenceById(dto.idPet()))
                .willReturn(pet);
        BDDMockito
                .given(tutorRepository
                        .getReferenceById(dto.idTutor()))
                .willReturn(tutor);
        BDDMockito.given(pet.getAbrigo())
                .willReturn(abrigo);
        validation.add(validador1);
        validation.add(validador2);
        //ACT
        adocaoService.solicitar(dto);
        //ASSERT
        BDDMockito.then(validador1).should().validar(dto);
        BDDMockito.then(validador2).should().validar(dto);
    }
}