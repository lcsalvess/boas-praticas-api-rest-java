package br.com.alura.adopet.api.validations.abrigo;

import br.com.alura.adopet.api.dto.abrigo.AbrigoDtoCadastro;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationTelefoneCadastradoAbrigo implements ValidationCadastroAbrigo {
    @Autowired
    private TutorRepository repository;

    public void validar (AbrigoDtoCadastro dto) {
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());
        if (telefoneJaCadastrado) {
            throw new ValidacaoExpection("Telefone já cadastrado para outro tutor!");
        }
    }

}
