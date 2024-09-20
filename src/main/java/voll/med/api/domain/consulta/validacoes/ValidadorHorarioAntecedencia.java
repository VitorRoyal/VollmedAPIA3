package voll.med.api.domain.consulta.validacoes;

import org.springframework.stereotype.Component;
import voll.med.api.domain.ValidacaoException;
import voll.med.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

@Component //Podia ser o @Service tb
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{

    public void validar (DadosAgendamentoConsulta dados) {

        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência minima de 30 minutos");
        }

    }
}
