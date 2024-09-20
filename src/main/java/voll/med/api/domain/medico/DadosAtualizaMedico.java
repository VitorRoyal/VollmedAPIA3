package voll.med.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import voll.med.api.domain.endereco.DadosEndereco;

public record DadosAtualizaMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {


}
