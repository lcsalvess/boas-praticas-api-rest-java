package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationTutorComLimiteDeAdocoes implements ValidationSolicitacaoAdocao {
    @Autowired
    private AdocaoRepository adocaoRepository;
    public void validar(SolicitacaoAdocaoDto dto) {
        Integer adocoesTutor = adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO);

        if (adocoesTutor == 5) {
            throw new ValidacaoExpection("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }
}
