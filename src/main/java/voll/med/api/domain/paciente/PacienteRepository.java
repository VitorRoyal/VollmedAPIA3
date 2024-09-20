package voll.med.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<voll.med.api.domain.paciente.Paciente, Long> {
    Page<voll.med.api.domain.paciente.Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select p.ativo
            from Paciente p
            where
            p.id = :id
            """)
    Boolean findAtivoById(Long id);
}
