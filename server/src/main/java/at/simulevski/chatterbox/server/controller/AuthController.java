package at.simulevski.chatterbox.server.controller;

import at.simulevski.chatterbox.server.domain.UserDto;
import at.simulevski.chatterbox.server.domain.transactional.CreateUserRequest;
import at.simulevski.chatterbox.server.domain.transactional.LoginUserRequest;
import at.simulevski.chatterbox.server.domain.transactional.LoginUserResponse;
import at.simulevski.chatterbox.server.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }
}
