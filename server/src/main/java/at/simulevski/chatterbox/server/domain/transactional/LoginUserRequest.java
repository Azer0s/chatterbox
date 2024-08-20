package at.simulevski.chatterbox.server.domain.transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginUserRequest {
    private String username;
    private String password;
}
