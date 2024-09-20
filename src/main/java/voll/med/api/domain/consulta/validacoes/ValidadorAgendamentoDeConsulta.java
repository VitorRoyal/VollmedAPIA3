package voll.med.api.domain.consulta.validacoes;

import voll.med.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    //Todos metodos de interface sao publicos
    void validar (DadosAgendamentoConsulta dados);

}
