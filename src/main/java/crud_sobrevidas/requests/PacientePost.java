package crud_sobrevidas.requests;

import crud_sobrevidas.enums.NivelDePrioridade;
import crud_sobrevidas.enums.Sexo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacientePost {
	private String nome;
	private String cpf;
	private String dataDeNascimento;
	private String nomeDaMae;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	private String cartaoDoSus;
	private String telefone01;
	private String telefone02;
	private String email;
	private String cep;
	private String bairro;
	private String logradouro;
	private String complemento;
	private Integer numero;
	private Integer idade;
	private boolean tabagista;
	private boolean etilista;
	@Enumerated(EnumType.STRING)
	private NivelDePrioridade nivelDePrioridade;
	private boolean lesaoSuspeita;
}
