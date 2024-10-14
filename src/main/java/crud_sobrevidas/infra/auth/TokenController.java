package crud_sobrevidas.infra.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;

@RequestMapping("/token")
@RestController
public class TokenController {

    @Operation(summary = "Faz a requisição de token para o keycloak e retorna um token", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação de token feita com sucesso"),
            @ApiResponse(responseCode = "401", description = "Conexão com o keycloak foi recusada")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Details of the Item to be created",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = User.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "admin_sobrevidas",
                                    value = "{\"clientId\": \"app_sobrevidas\", \"username\":\"admin_sobrevidas\", \"password\":\"admin\", \"grantType\":\"password\"}",
                                    summary = "Requisição como Admin"),
                            @ExampleObject(
                                    name = "user_sobrevidas",
                                    value = "{\"clientId\": \"app_sobrevidas\", \"username\":\"user_sobrevidas\", \"password\":\"user\", \"grantType\":\"password\"}",
                                    summary = "Requisição como User") }))
    @PostMapping("/")
    public ResponseEntity<String> token(@RequestBody User user){
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", user.getClientId());
        formData.add("username", user.getUsername());
        formData.add("password", user.getPassword());
        formData.add("grant_type", user.getGrantType());

        HttpEntity<MultiValueMap<String, String>> httpEntity =
                new HttpEntity<MultiValueMap<String, String>>(formData, headers);

        ResponseEntity<String> result = restTemplate.postForEntity("http://keycloak:8080/realms/sobrevidas/protocol/openid-connect/token", httpEntity, String.class);
        return result;
    }
}
