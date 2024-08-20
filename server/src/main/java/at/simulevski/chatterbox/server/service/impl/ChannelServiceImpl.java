package at.simulevski.chatterbox.server.service.impl;

import at.simulevski.chatterbox.server.domain.ChannelDto;
import at.simulevski.chatterbox.server.domain.transactional.CreateChannelRequest;
import at.simulevski.chatterbox.server.exception.ChannelExistsException;
import at.simulevski.chatterbox.server.mapper.ChannelMapper;
import at.simulevski.chatterbox.server.persistence.ChannelRepository;
import at.simulevski.chatterbox.server.service.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper;

    @Override
    public ChannelDto createChannel(CreateChannelRequest request) {
        if (channelRepository.findByName(request.getName()).isPresent()) {
            throw new ChannelExistsException(request.getName());
        }

        var channel = channelMapper.toEntity(ChannelDto.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build());

        return channelMapper.toDto(channelRepository.save(channel));
    }

    @Override
    public void deleteChannel(String name) {
        channelRepository.deleteByName(name);
    }

    @Override
    public List<ChannelDto> getChannels() {
        return channelRepository.findAll()
                .stream()
                .map(channelMapper::toDto)
                .toList();
    }
}
