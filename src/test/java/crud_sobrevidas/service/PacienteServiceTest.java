package crud_sobrevidas.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import crud_sobrevidas.domain.Paciente;
import crud_sobrevidas.repository.PacienteRepository;
import crud_sobrevidas.requests.PacientePost;
import crud_sobrevidas.util.CriarPaciente;
import crud_sobrevidas.util.CriarPacientePost;

@ExtendWith(SpringExtension.class)
class PacienteServiceTest {

	@InjectMocks
	private PacienteService pacienteService;
	
	@Mock
	private PacienteRepository pacienteRepositorio;
	
	@BeforeEach
	public void setup() {
		BDDMockito.when(pacienteRepositorio.findAll())
				.thenReturn(List.of(CriarPaciente.criarPacienteValido()));
		BDDMockito.when(pacienteRepositorio.save(ArgumentMatchers.any(Paciente.class)))
				.thenReturn(CriarPaciente.criarPacienteValido());
		BDDMockito.when(pacienteRepositorio.findById(ArgumentMatchers.anyLong()))
				.thenReturn(Optional.of(CriarPaciente.criarPacienteValido()));
		BDDMockito.when(pacienteRepositorio.findByNome(ArgumentMatchers.anyString()))
				.thenReturn(List.of(CriarPaciente.criarPacienteValido()));
		BDDMockito.doNothing().when(pacienteRepositorio).delete(ArgumentMatchers.any(Paciente.class));
	}
	
	@Test
	@DisplayName("listarTodos retorna uma lista de Paciente quando bem sucedido")
	void listarTodos_RetornaListaDePacientes_QuandoBemSucedido() {
		Paciente pacienteEsperado = CriarPaciente.criarPacienteValido();
		List<Paciente> pacientes = pacienteService.listarTodos();
		Assertions.assertThat(pacientes).isNotNull().isNotEmpty().hasSize(1);
		Assertions.assertThat(pacientes.get(0)).isEqualTo(pacienteEsperado);
	}
	
	@Test
	@DisplayName("salvar retorna um Paciente quando bem sucedido")
	void salvar_RetornaPaciente_QuandoBemSucedido() {
		PacientePost pacientePost = CriarPacientePost.criarPacientePostValido();
		Paciente pacienteSalvo = pacienteService.salvarPaciente(pacientePost);
		Assertions.assertThat(pacienteSalvo).isNotNull();
		Assertions.assertThat(pacienteSalvo.getNome()).isEqualTo(pacientePost.getNome());
	}
	
	@Test
	@DisplayName("encontrarPeloId retorna um Paciente quando bem sucedido")
	void encontrarPeloId_RetornaPaciente_QuandoBemSucedido() {
		Long idEsperado = CriarPaciente.criarPacienteValido().getId();
		Paciente pacienteEncontrado = pacienteService.encontrarPeloId(1L);
		Assertions.assertThat(pacienteEncontrado).isNotNull();
		Assertions.assertThat(pacienteEncontrado.getId()).isEqualTo(idEsperado);
	}
	
	@Test
	@DisplayName("encontrarPeloNome retorna um Paciente quando bem sucedido")
	void encontrarPeloNome_RetornaPaciente_QuandoBemSucedido() {
		String nomeEsperado = CriarPaciente.criarPacienteValido().getNome();
		List<Paciente> pacientesEncontrados = pacienteService.encontrarPeloNome(nomeEsperado);
		Assertions.assertThat(pacientesEncontrados).isNotEmpty().isNotNull().hasSize(1);
		Assertions.assertThat(pacientesEncontrados.get(0).getNome()).isEqualTo(nomeEsperado);
	}
	
	@Test
	@DisplayName("remover retorna Void quando bem sucedido")
	void remover_RetornaVoid_QuantoBemSucedido() {
		Assertions.assertThatCode(() -> pacienteService.remover(1L))
				.doesNotThrowAnyException();
	}
	
	@Test
	@DisplayName("update retorna void quando bem sucedido")
	void update_RetornaVoid_QuandoBemSucedido() {
		Assertions.assertThatCode(() -> pacienteService.update(CriarPaciente.criarPacientePutValido()))
				.doesNotThrowAnyException();
	}


}
