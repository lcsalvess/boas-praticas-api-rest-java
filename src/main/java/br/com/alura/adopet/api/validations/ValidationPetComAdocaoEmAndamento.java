package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationPetComAdocaoEmAndamento implements  ValidationSolicitacaoAdocao {
    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        boolean petTemAdocaoEmAndamento = adocaoRepository
                .existsByPetIdAndStatus
                        (dto.idPet(),
                                StatusAdocao.AGUARDANDO_AVALIACAO);
        if (petTemAdocaoEmAndamento) {
            throw new ValidacaoExpection("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
