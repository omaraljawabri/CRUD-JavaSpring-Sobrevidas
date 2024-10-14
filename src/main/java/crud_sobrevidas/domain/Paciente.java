package crud_sobrevidas.domain;

import crud_sobrevidas.enums.NivelDePrioridade;
import crud_sobrevidas.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "paciente")
public class Paciente {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Schema(description = "Identificador único do paciente", example = "1")
	private Long id;
	@Schema(description = "Nome completo do paciente", example = "Ronaldo Martins", type = "int")
	private String nome;
	@Schema(description = "CPF do paciente", example = "764.467.472-37")
	private String cpf;
	@Schema(description = "Data de nascimento do paciente", example = "10/11/1970")
	private String dataDeNascimento;
	@Schema(description = "Nome da mãe do paciente", example = "Neuza Martins")
	private String nomeDaMae;
	@Enumerated(EnumType.STRING)
	@Schema(description = "Sexo do paciente", example = "MASCULINO")
	private Sexo sexo;
	@Schema(description = "Cartão do SUS do paciente", example = "542894591386350")
	private String cartaoDoSus;
	@Schema(description = "Primeiro telefone do paciente", example = "(62)98312-4305")
	private String telefone01;
	@Schema(description = "Segundo telefone do paciente (número reserva)", example = "(62)94501-2474")
	private String telefone02;
	@Schema(description = "Email do paciente", example = "ronaldo@gmail.com")
	private String email;
	@Schema(description = "CEP do endereço do paciente", example = "12345-675")
	private String cep;
	@Schema(description = "Bairro em que o paciente mora", example = "Setor Oeste")
	private String bairro;
	@Schema(description = "Logradouro do endereço do paciente", example = "Rua Jequitimar")
	private String logradouro;
	@Schema(description = "Complemento do endereço do paciente", example = "Ed. Rosas, Apt 504")
	private String complemento;
	@Schema(description = "Número do endereço do paciente", example = "564")
	private Integer numero;
	@Schema(description = "Idade do paciente", example = "54")
	private Integer idade;
	@Schema(description = "Indica se o paciente é tabagista ou não", example = "true")
	private boolean tabagista;
	@Schema(description = "Indica se o paciente é etilista ou não", example = "false")
	private boolean etilista;
	@Schema(description = "Indica o nível de prioridade do paciente", example = "MEDIO")
	@Enumerated(EnumType.STRING)
	private NivelDePrioridade nivelDePrioridade;
	@Schema(description = "Indica se o paciente tem uma lesão suspeita na boca ou não", example = "false")
	private boolean lesaoSuspeita;
}
