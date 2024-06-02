package br.com.alura.adopet.api.validations.abrigo;

import br.com.alura.adopet.api.dto.abrigo.AbrigoDtoCadastro;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationNomeCadastrado implements ValidationCadastroAbrigo {
    @Autowired
    private AbrigoRepository repository;

        public void validar (AbrigoDtoCadastro dto) {
            boolean nomeJaCadastrado = repository.existsByNome(dto.nome());
        if (nomeJaCadastrado) {
            throw new ValidacaoExpection("E-mail já cadastrado para outro tutor!");
        }
    }

}
