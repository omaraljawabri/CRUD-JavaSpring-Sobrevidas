package crud_sobrevidas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import crud_sobrevidas.domain.Paciente;
import crud_sobrevidas.repository.PacienteRepository;
import crud_sobrevidas.requests.PacientePost;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {
	private final PacienteRepository pacienteRepositorio;
	
	public List<Paciente> listarTodos(){
		return pacienteRepositorio.findAll();
	}
	
	public Paciente salvarPaciente(PacientePost pacientePost) {
		return pacienteRepositorio.save(Paciente.builder().nome(pacientePost.getNome()) //MapStruct não funcionou
				.cpf(pacientePost.getCpf())
				.dataDeNascimento(pacientePost.getDataDeNascimento())
				.nomeDaMae(pacientePost.getNomeDaMae())
				.sexo(pacientePost.getSexo())
				.cartaoDoSus(pacientePost.getCartaoDoSus())
				.telefone01(pacientePost.getTelefone01())
				.telefone02(pacientePost.getTelefone02())
				.email(pacientePost.getEmail())
				.cep(pacientePost.getCep())
				.bairro(pacientePost.getBairro())
				.logradouro(pacientePost.getLogradouro())
				.complemento(pacientePost.getComplemento())
				.numero(pacientePost.getNumero())
				.idade(pacientePost.getIdade())
				.tabagista(pacientePost.isTabagista())
				.etilista(pacientePost.isEtilista())
				.nivelDePrioridade(pacientePost.getNivelDePrioridade())
				.lesaoSuspeita(pacientePost.isLesaoSuspeita())
				.build());
	}
	
	public Paciente encontrarPeloId(Long id){
		return pacienteRepositorio.findById(id).orElseThrow();
	}
	
	public void remover(Long id) {
		pacienteRepositorio.delete(encontrarPeloId(id));
	}

	
	public List<Paciente> encontrarPeloNome(String nome){
		return pacienteRepositorio.findByNome(nome);
	}
	
	public void update(Paciente paciente){
		Paciente pacienteSalvo = encontrarPeloId(paciente.getId());
		paciente.setId(pacienteSalvo.getId());
		pacienteRepositorio.save(paciente);
	}
}
