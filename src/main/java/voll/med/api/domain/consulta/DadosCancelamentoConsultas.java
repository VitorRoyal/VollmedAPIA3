package voll.med.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsultas(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivo) {
}
