package br.com.alura.adopet.api.validations.tutor;

import br.com.alura.adopet.api.dto.tutor.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationTelefoneCadastrado implements ValidationCadastroTutor {
    @Autowired
    private TutorRepository repository;

    public void validar (CadastroTutorDto dto) {
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());
        if (telefoneJaCadastrado) {
            throw new ValidacaoExpection("Telefone já cadastrado para outro tutor!");
        }
    }

}
