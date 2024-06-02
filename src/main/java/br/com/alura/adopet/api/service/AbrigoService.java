package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.AbrigoDto;
import br.com.alura.adopet.api.dto.abrigo.AbrigoDtoCadastro;
import br.com.alura.adopet.api.dto.pet.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validations.abrigo.ValidationCadastroAbrigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {
    @Autowired
    private AbrigoRepository abrigoRepository;
    @Autowired
    private List<ValidationCadastroAbrigo> validation;
    @Autowired
    private PetRepository petRepository;

    public List<AbrigoDto> listarAbrigos() {
        return abrigoRepository
                .findAll()
                .stream()
                .map(AbrigoDto::new)
                .toList();
    }

    public void cadastrar(AbrigoDtoCadastro dto) {
        validation.forEach(v -> v.validar(dto));
        var abrigo = new Abrigo(dto);
        abrigoRepository.save(abrigo);
    }

    public List<PetDto> listarPetsPorAbrigo(String idOuNome) {
        Abrigo abrigo = carregarAbrigo(idOuNome);

        return petRepository
                .findByAbrigo(abrigo)
                .stream()
                .map(PetDto::new)
                .toList();
    }

    public Abrigo carregarAbrigo(String idOuNome) {
        Optional<Abrigo> optionalAbrigo;
        try {
            Long id = Long.parseLong(idOuNome);
            optionalAbrigo = abrigoRepository.findById(id);
        } catch (NumberFormatException e) {
            optionalAbrigo = abrigoRepository.findByNome(idOuNome);
        }
        return optionalAbrigo.orElseThrow(() -> new ValidacaoExpection("Abrigo não encontrado"));
    }
}
