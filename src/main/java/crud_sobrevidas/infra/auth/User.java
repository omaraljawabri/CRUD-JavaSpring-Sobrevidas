package crud_sobrevidas.infra.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
public class User {
    private String clientId;
    private String username;
    private String password;
    private String grantType;
}
