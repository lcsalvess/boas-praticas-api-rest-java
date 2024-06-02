package br.com.alura.adopet.api.validations.tutor;

import br.com.alura.adopet.api.dto.tutor.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationEmailCadastradoTutor implements ValidationCadastroTutor{
    @Autowired
    private TutorRepository repository;

    public void validar (CadastroTutorDto dto) {
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());
        if (emailJaCadastrado) {
            throw new ValidacaoExpection("E-mail já cadastrado para outro tutor!");
        }
    }

}
