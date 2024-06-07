package br.com.alura.adopet.api.dto.adocao;

import br.com.alura.adopet.api.model.Adocao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoAdocaoDto(
        @NotNull
        Long idPet,
        @NotNull
        Long idTutor,
        @NotBlank
        String motivo) {
        public SolicitacaoAdocaoDto (Adocao adocao) {
                this(adocao.getPet().getId(), adocao.getTutor().getId(), adocao.getMotivo());
        }
}
