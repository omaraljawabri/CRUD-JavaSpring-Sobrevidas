package crud_sobrevidas;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "CRUD de Paciente - Sobrevidas", version = "1", description = "CRUD de Paciente para o projeto Sobrevidas"))
public class CrudSobrevidasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSobrevidasApplication.class, args);
	}

}
