package crud_sobrevidas.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import crud_sobrevidas.service.PacienteService;
import crud_sobrevidas.domain.Paciente;
import crud_sobrevidas.requests.PacientePost;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
@SecurityRequirement(name = "securityConfig")
public class PacienteController {
	private final PacienteService pacienteService;
	
	@Operation(summary = "Busca por todos os pacientes cadastrados", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Usuário não foi autorizado"),
			@ApiResponse(responseCode = "403", description = "Usuário não tem a permissão necessária para efetuar a operação"),
			@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
	})
	@GetMapping
	public ResponseEntity<List<Paciente>> listarTodos(){
		return ResponseEntity.ok(pacienteService.listarTodos());
	}
	
	@Operation(summary = "Busca por paciente pelo id", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Usuário não foi autorizado"),
			@ApiResponse(responseCode = "403", description = "Usuário não tem a permissão necessária para efetuar a operação"),
			@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
	})
	@GetMapping(path = "/{id}")
	public ResponseEntity<Paciente> encontrarPeloId(@PathVariable long id){
		return ResponseEntity.ok(pacienteService.encontrarPeloId(id));
	}
	
	@Operation(summary = "Busca paciente pelo nome", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
			@ApiResponse(responseCode = "401", description = "Usuário não foi autorizado"),
			@ApiResponse(responseCode = "403", description = "Usuário não tem a permissão necessária para efetuar a operação"),
			@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
	})
	@GetMapping(path =  "/pesquisar")
	public ResponseEntity<List<Paciente>> encontrarPeloNome(@RequestParam String nome){
		return ResponseEntity.ok(pacienteService.encontrarPeloNome(nome));
	}
	
	@Operation(summary = "Cadastra um paciente no banco de dados", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Paciente criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos ou faltando"),
			@ApiResponse(responseCode = "401", description = "Usuário não foi autorizado"),
			@ApiResponse(responseCode = "403", description = "Usuário não tem a permissão necessária para efetuar a operação"),
			@ApiResponse(responseCode = "500", description = "Erro ao realizar cadastro de paciente")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Paciente> salvarPaciente(@RequestBody @Valid PacientePost pacientePost){
		return new ResponseEntity<>(pacienteService.salvarPaciente(pacientePost), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Atualiza paciente no banco de dados", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Paciente atualizado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Usuário não foi autorizado"),
			@ApiResponse(responseCode = "403", description = "Usuário não tem a permissão necessária para efetuar a operação"),
			@ApiResponse(responseCode = "500", description = "Erro ao atualizar paciente")
	})
	@PutMapping
	public ResponseEntity<Void> update(@Valid @RequestBody Paciente paciente){
		pacienteService.update(paciente);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Operation(summary = "Deleta paciente de acordo com o id passado", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Paciente deletado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Usuário não foi autorizado"),
			@ApiResponse(responseCode = "403", description = "Usuário não tem a permissão necessária para efetuar a operação"),
			@ApiResponse(responseCode = "500", description = "Erro ao deletar paciente")
	})
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletarPaciente(@PathVariable long id) {
		pacienteService.remover(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
