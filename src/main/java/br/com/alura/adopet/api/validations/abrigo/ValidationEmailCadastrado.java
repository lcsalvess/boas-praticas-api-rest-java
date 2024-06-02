package br.com.alura.adopet.api.validations.abrigo;

import br.com.alura.adopet.api.dto.abrigo.AbrigoDtoCadastro;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationEmailCadastrado implements ValidationCadastroAbrigo {
    @Autowired
    private AbrigoRepository repository;

        public void validar (AbrigoDtoCadastro dto) {
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());
        if (emailJaCadastrado) {
            throw new ValidacaoExpection("E-mail já cadastrado para outro tutor!");
        }
    }

}
