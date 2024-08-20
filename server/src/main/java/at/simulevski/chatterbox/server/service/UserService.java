package at.simulevski.chatterbox.server.service;

import at.simulevski.chatterbox.server.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<UserDto> findByUsername(String username);

    List<UserDto> findAll();
}
