package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationPetOutraSolicitacao implements ValidationSolicitacaoAdocao {
    @Autowired
    private AdocaoRepository repository;

    public void validar (SolicitacaoAdocaoDto dto) {
        boolean petTemAdocaoEmAndamento = repository.existsByPetIdAndStatus(
                dto.idPet(),
                StatusAdocao.AGUARDANDO_AVALIACAO);
        if (petTemAdocaoEmAndamento) {
            throw new ValidacaoExpection("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
