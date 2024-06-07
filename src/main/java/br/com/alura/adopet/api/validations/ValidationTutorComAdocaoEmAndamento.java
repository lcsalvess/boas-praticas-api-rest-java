package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoExpection;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationTutorComAdocaoEmAndamento implements ValidationSolicitacaoAdocao{
    @Autowired
    private AdocaoRepository adocaoRepository;
    public void validar(SolicitacaoAdocaoDto dto) {
        boolean tutorTemAdocaoEmAndamento = adocaoRepository
                .existsByTutorIdAndStatus
                        (dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if (tutorTemAdocaoEmAndamento) {
            throw new ValidacaoExpection("Tutor já possui outra adoção aguardando avaliação!");
        }

    }
}
