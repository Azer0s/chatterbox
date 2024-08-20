package at.simulevski.chatterbox.server.service.impl;

import at.simulevski.chatterbox.server.domain.UserDto;
import at.simulevski.chatterbox.server.mapper.UserMapper;
import at.simulevski.chatterbox.server.persistence.UserRepository;
import at.simulevski.chatterbox.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
