package crud_sobrevidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import crud_sobrevidas.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	List<Paciente> findByNome(String nome);
}
