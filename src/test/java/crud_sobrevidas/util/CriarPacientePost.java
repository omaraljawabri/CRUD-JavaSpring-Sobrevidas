package crud_sobrevidas.util;

import crud_sobrevidas.enums.NivelDePrioridade;
import crud_sobrevidas.enums.Sexo;
import crud_sobrevidas.requests.PacientePost;

public class CriarPacientePost {
	public static PacientePost criarPacientePostValido() {
		return PacientePost.builder().nome("Ronaldo").cpf("764.467.472-37").dataDeNascimento("10/11/1970")
				.nomeDaMae("Neuza").sexo(Sexo.MASCULINO).cartaoDoSus("396210861-3968").telefone01("6298312-4305")
				.telefone02("6294501-2474").email("ronaldo@gmail.com").cep("387374389").bairro("Leste")
				.logradouro("A").complemento("B").numero(544).idade(54).tabagista(true).etilista(false)
				.nivelDePrioridade(NivelDePrioridade.ALTO).lesaoSuspeita(false).build();
	}
}
