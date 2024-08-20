package at.simulevski.chatterbox.server.mapper;

import at.simulevski.chatterbox.server.domain.UserDto;
import at.simulevski.chatterbox.server.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto toDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserDto userDTO);
}
