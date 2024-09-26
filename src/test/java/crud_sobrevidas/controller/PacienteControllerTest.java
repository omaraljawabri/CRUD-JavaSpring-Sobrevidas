package crud_sobrevidas.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
import crud_sobrevidas.requests.PacientePost;
import crud_sobrevidas.service.PacienteService;
import crud_sobrevidas.util.CriarPaciente;
import crud_sobrevidas.util.CriarPacientePost;

@ExtendWith(SpringExtension.class)
class PacienteControllerTest {

	@InjectMocks
	private PacienteController pacienteController;
	
	@Mock
	private PacienteService pacienteService;
	
	@BeforeEach
	public void setup() {
		BDDMockito.when(pacienteService.listarTodos())
				.thenReturn(List.of(CriarPaciente.criarPacienteValido()));
		BDDMockito.when(pacienteService.encontrarPeloId(ArgumentMatchers.anyLong()))
				.thenReturn(CriarPaciente.criarPacienteValido());
		BDDMockito.when(pacienteService.encontrarPeloNome(ArgumentMatchers.anyString()))
				.thenReturn(List.of(CriarPaciente.criarPacienteValido()));
		BDDMockito.when(pacienteService.salvarPaciente(ArgumentMatchers.any(PacientePost.class)))
				.thenReturn(CriarPaciente.criarPacienteValido());
		BDDMockito.doNothing().when(pacienteService).remover(ArgumentMatchers.anyLong());
	}
	
	@Test
	@DisplayName("listarTodos retorna uma lista de Paciente quando bem sucedido")
	void listarTodos_RetornaListaDePaciente_QuandoBemSucedido() {
		Paciente pacienteValido = CriarPaciente.criarPacienteValido();
		List<Paciente> pacientes = pacienteController.listarTodos().getBody();
		Assertions.assertThat(pacientes).isNotNull().isNotEmpty().hasSize(1);
		Assertions.assertThat(pacientes.get(0)).isEqualTo(pacienteValido);
	}
	
	@Test
	@DisplayName("encontrarPeloId retorna um Paciente quando bem sucedido")
	void encontrarPeloId_RetornaPaciente_QuandoBemSucedido() {
		Long idEsperado = CriarPaciente.criarPacienteValido().getId();
		Paciente paciente = pacienteController.encontrarPeloId(1L).getBody();
		Assertions.assertThat(paciente).isNotNull();
		Assertions.assertThat(paciente.getId()).isEqualTo(idEsperado);
	}

	@Test
	@DisplayName("encontrarPeloNome retorna uma lista de Paciente quando bem sucedido")
	void encontrarPeloNome_RetornaListaDePaciente_QuandoBemSucedido() {
		String nomeEsperado = CriarPaciente.criarPacienteValido().getNome();
		List<Paciente> pacientes = pacienteController.encontrarPeloNome(nomeEsperado).getBody();
		Assertions.assertThat(pacientes).isNotNull().isNotEmpty().hasSize(1);
		Assertions.assertThat(pacientes.get(0).getNome()).isEqualTo(nomeEsperado);
	}
	
	@Test
	@DisplayName("salvar retorna um Paciente quando bem sucedido")
	void salvar_RetornaPaciente_QuandoBemSucedido() {
		Paciente pacienteValido = CriarPaciente.criarPacienteValido();
		Paciente pacienteSalvo = pacienteController.salvarPaciente(CriarPacientePost.criarPacientePostValido()).getBody();
		Assertions.assertThat(pacienteSalvo).isNotNull();
		Assertions.assertThat(pacienteSalvo.getNome()).isEqualTo(pacienteValido.getNome());
	}
	
	@Test
	@DisplayName("deletarPaciente retorna void quando bem sucedido")
	void remover_RetornaVoid_QuandoBemSucedido() {
		Assertions.assertThatCode(() -> pacienteController.deletarPaciente(1L))
				.doesNotThrowAnyException();
	}
	
	@Test
	@DisplayName("update retorna void quando bem sucedido")
	void update_RetornaVoid_QuandoBemSucedido() {
		Assertions.assertThatCode(() -> pacienteController.update(CriarPaciente.criarPacientePutValido()))
				.doesNotThrowAnyException();
	}

}
