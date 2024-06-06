package br.com.alura.adopet.api.validations.adocao;

import br.com.alura.adopet.api.dto.abrigo.AbrigoDtoCadastro;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.dto.pet.PetDtoCadastro;
import br.com.alura.adopet.api.dto.tutor.CadastroTutorDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.PetService;
import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ValidationPetOutraSolicitacaoTest {
    @InjectMocks
    ValidationPetOutraSolicitacao validation;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private PetService petService;
    @Mock
    private TutorService tutorService;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private AbrigoRepository abrigoRepository;
    @Mock
    private Pet pet;
    @Mock
    private Tutor tutor;
    @Mock
    private Abrigo abrigo;
    @Mock
    private SolicitacaoAdocaoDto dto;
    @Mock
    private AbrigoDtoCadastro dtoCadastro;
    @Mock
    private PetDtoCadastro dtoPetCadastro;
    @Test
    void cenario1() {
        CadastroTutorDto dtoTutor = new CadastroTutorDto(
                "Lucas",
                "11999446678",
                "lucas0asm@outlook.com");
        tutorService.cadastrar(dtoTutor);
        verify(tutorRepository,times(1)).save(tutor);
//        tutor.setId(2L);
//        tutorService.cadastrar(tutor);
//        Abrigo abrigo = new Abrigo(
//                new AbrigoDtoCadastro(
//                        "Erika",
//                        "11996051710",
//                        "123456@gmail.com"));
//        abrigo.setId(1L);
//        when(abrigoRepository.save(abrigo)).thenReturn(abrigo);
//        PetDtoCadastro dtoPetCadastro = Mockito.mock(PetDtoCadastro.class);
//        when(dtoPetCadastro.tipo()).thenReturn(any());
//        when(dtoPetCadastro.nome()).thenReturn("SCAR");
//        when(dtoPetCadastro.raca()).thenReturn("SRD");
//        when(dtoPetCadastro.idade()).thenReturn(10);
//        when(dtoPetCadastro.cor()).thenReturn("Caramelo");
//        when(dtoPetCadastro.peso()).thenReturn(10.0f);
//        Pet pet = new Pet(dtoPetCadastro, abrigo);
//        pet.setId(1L);
//        petService.cadastrarPet(abrigo, dtoPetCadastro);
//        var solicitacao1 = new Adocao(tutor, pet,  "qualquer motivo");
//        when(adocaoRepository.save(solicitacao1)).thenReturn(solicitacao1);
//        var solicitacao2 = new Adocao(tutor2, pet,  "qualquer motivo");
//        when(adocaoRepository.save(solicitacao1)).thenReturn(solicitacao2);
//        Assertions.assertDoesNotThrow(() -> validation.validar(dto));
    }

}