package voll.med.api.domain.consulta.validacoes;

import org.springframework.stereotype.Component;
import voll.med.api.domain.ValidacaoException;
import voll.med.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void validar (DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY); //Checar se a data é domingo

        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;//Checar se a hora é antes das 7 horas
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;//Checar se hora é depois das 19 pq a consulta tem 1 hr

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horario de funcionamento da clínica");
        }

    }

}
