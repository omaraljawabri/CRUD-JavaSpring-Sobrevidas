package crud_sobrevidas.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import crud_sobrevidas.domain.Paciente;
import crud_sobrevidas.util.CriarPaciente;


@DataJpaTest
@DisplayName("Testes para camada repository")
class PacienteRepositoryTest {

	@Autowired
	private PacienteRepository pacienteRepositorio;
	
	@Test
	@DisplayName("Save persiste um Paciente no banco de dados quando bem sucedido")
	void save_PersistePaciente_QuandoBemSucedido(){
		Paciente pacientePost = CriarPaciente.criarPacientePost();
		Paciente pacienteSalvo = this.pacienteRepositorio.save(pacientePost);
		Assertions.assertThat(pacienteSalvo).isNotNull();
		Assertions.assertThat(pacienteSalvo.getId()).isNotNull();
		Assertions.assertThat(pacienteSalvo.getNome()).isEqualTo(pacientePost.getNome());
	}
	
	@Test
	@DisplayName("Save atualiza um Paciente no banco de dados quando bem sucedido")
	void save_AtualizaPaciente_QuandoBemSucedido() {
		Paciente pacientePost = CriarPaciente.criarPacientePost();
		Paciente pacienteSalvo = this.pacienteRepositorio.save(pacientePost);
		pacienteSalvo.setNome("Leonardo");
		Paciente pacienteAtualizado = this.pacienteRepositorio.save(pacienteSalvo);
		Assertions.assertThat(pacienteAtualizado).isNotNull();
		Assertions.assertThat(pacienteAtualizado.getId()).isNotNull();
		Assertions.assertThat(pacienteAtualizado.getNome()).isEqualTo(pacienteSalvo.getNome());
	}
	
	@Test
	@DisplayName("delete remove um Paciente do banco de dados quando bem sucedido")
	void delete_RemovePaciente_QuandoBemSucedido() {
		Paciente pacientePost = CriarPaciente.criarPacientePost();
		Paciente pacienteSalvo = pacienteRepositorio.save(pacientePost);
		pacienteRepositorio.delete(pacienteSalvo);
		Optional<Paciente> pacienteOptional = pacienteRepositorio.findById(pacienteSalvo.getId());
		Assertions.assertThat(pacienteOptional).isEmpty();
	}
	
	@Test
	@DisplayName("findByNome retorna uma lista de Paciente quando bem sucedido")
	void findByNome_RetornaListaDePaciente_QuandoBemSucedido() {
		Paciente pacientePost = CriarPaciente.criarPacientePost();
		Paciente pacienteSalvo = pacienteRepositorio.save(pacientePost);
		String nome = pacienteSalvo.getNome();
		List<Paciente> pacientes = pacienteRepositorio.findByNome(nome);
		Assertions.assertThat(pacientes).isNotNull().isNotEmpty().hasSize(1);
		Assertions.assertThat(pacientes).contains(pacienteSalvo);
	}
	
	@Test
	@DisplayName("findByNome retorna uma lista vazia quando nenhum Paciente é encontrado")
	void findByNome_RetornaListaVazia_QuandoBemSucedido() {
		List<Paciente> pacientes = pacienteRepositorio.findByNome("zzzzz");
		Assertions.assertThat(pacientes).isEmpty();
	}
	
	@Test
	@DisplayName("findById retorna um Paciente quando bem sucedido")
	void findById_RetornaPaciente_QuandoBemSucedido() {
		Paciente pacientePost = CriarPaciente.criarPacientePost();
		Paciente pacienteSalvo = pacienteRepositorio.save(pacientePost);
		Optional<Paciente> pacienteOptional = pacienteRepositorio.findById(pacienteSalvo.getId());
		Assertions.assertThat(pacienteOptional).isNotEmpty();
		Assertions.assertThat(pacienteOptional.get()).isEqualTo(pacienteSalvo);
	}
	
	@Test
	@DisplayName("findAll retorna uma Lista de Paciente quando bem sucedido")
	void findAll_RetornaListaDePaciente_QuandoBemSucedido() {
		Paciente pacientePost = CriarPaciente.criarPacientePost();
		Paciente pacientePost2 = CriarPaciente.criarPacientePost();
		pacientePost2.setNome("Leonardo");
		pacienteRepositorio.save(pacientePost);
		pacienteRepositorio.save(pacientePost2);
		List<Paciente> pacientes = pacienteRepositorio.findAll();
		Assertions.assertThat(pacientes).isNotNull().isNotEmpty().hasSize(2);
		Assertions.assertThat(pacientes.get(0)).isEqualTo(pacientePost);
		Assertions.assertThat(pacientes.get(1)).isEqualTo(pacientePost2);
	}
}
