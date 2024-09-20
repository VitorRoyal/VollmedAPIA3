package voll.med.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voll.med.api.domain.consulta.AgendaDeConsultas;
import voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import voll.med.api.domain.consulta.DadosCancelamentoConsultas;
import voll.med.api.domain.consulta.DadosDetalhamentoConsulta;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar (@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);

    }

    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsultas dados){
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
