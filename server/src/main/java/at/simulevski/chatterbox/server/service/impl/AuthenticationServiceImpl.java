package at.simulevski.chatterbox.server.service.impl;

import at.simulevski.chatterbox.server.domain.UserDto;
import at.simulevski.chatterbox.server.domain.UserRoles;
import at.simulevski.chatterbox.server.domain.transactional.CreateUserRequest;
import at.simulevski.chatterbox.server.domain.transactional.LoginUserRequest;
import at.simulevski.chatterbox.server.domain.transactional.LoginUserResponse;
import at.simulevski.chatterbox.server.exception.UserExistsException;
import at.simulevski.chatterbox.server.exception.UserNotFoundException;
import at.simulevski.chatterbox.server.mapper.UserMapper;
import at.simulevski.chatterbox.server.persistence.UserRepository;
import at.simulevski.chatterbox.server.service.AuthenticationService;
import at.simulevski.chatterbox.server.service.JwtService;
import at.simulevski.chatterbox.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder password;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserDto signup(CreateUserRequest request) {
        userService.findByUsername(request.getUsername()).ifPresent(_ -> {
            throw new UserExistsException(request.getUsername());
        });

        var user = userMapper.toEntity(UserDto.builder()
                .username(request.getUsername())
                .password(password.encode(request.getPassword()))
                .roles(List.of(UserRoles.USER))
                .build());

        var result = userRepository.save(user);
        return userMapper.toDto(result);
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userService.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(request.getUsername()));

        var token = jwtService.generateToken(user);

        return LoginUserResponse.builder()
                .token(token)
                .forUser(user)
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }
}
