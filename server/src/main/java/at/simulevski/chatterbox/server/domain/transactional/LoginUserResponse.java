package at.simulevski.chatterbox.server.domain.transactional;

import at.simulevski.chatterbox.server.domain.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginUserResponse {
    private String token;
    private long expiresIn;
    private UserDto forUser;
}
