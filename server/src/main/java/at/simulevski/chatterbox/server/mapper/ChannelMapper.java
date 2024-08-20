package at.simulevski.chatterbox.server.mapper;

import at.simulevski.chatterbox.server.domain.ChannelDto;
import at.simulevski.chatterbox.server.domain.ChannelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ChannelMapper {
    ChannelDto toDto(ChannelEntity entity);

    @Mapping(target = "id", ignore = true)
    ChannelEntity toEntity(ChannelDto dto);
}
