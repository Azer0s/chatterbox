package at.simulevski.chatterbox.server.service;

import at.simulevski.chatterbox.server.domain.UserDto;
import at.simulevski.chatterbox.server.domain.transactional.CreateUserRequest;
import at.simulevski.chatterbox.server.domain.transactional.LoginUserRequest;
import at.simulevski.chatterbox.server.domain.transactional.LoginUserResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    UserDto signup(CreateUserRequest request);

    LoginUserResponse login(LoginUserRequest request);
}
