package voll.med.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voll.med.api.domain.ValidacaoException;
import voll.med.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import voll.med.api.domain.medico.Medico;
import voll.med.api.domain.medico.MedicoRepository;
import voll.med.api.domain.paciente.PacienteRepository;

import java.util.List;

//Classe para executar as regras
//de negocio e validações da aplicação

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){ //! pra negar o IF (se n existir..)
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        //Como o id do medico é opcional de acordo com o trello
        // nos verificamos se ele ta vindo e se ele é valido
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));//Vai percorrer a lista 1 por 1 pra ver

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        if(medico == null) {
            throw new ValidacaoException("Não temos medicos disponiveis nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null) {
          throw new ValidacaoException("Especialidade é obrigatoria quando medico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsultas dados){
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

}
