package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.pet.PetDto;
import br.com.alura.adopet.api.dto.pet.PetDtoCadastro;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<PetDto> petsDisponiveis() {
        return petRepository
                .findAllByAdotadoFalse()
                .stream()
                .map(PetDto::new)
                .toList();
    }

    public void cadastrarPet(Abrigo abrigo, PetDtoCadastro dto) {
        petRepository.save(new Pet(dto, abrigo));
    }
}
