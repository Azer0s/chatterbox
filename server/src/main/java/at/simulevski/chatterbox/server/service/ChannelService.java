package at.simulevski.chatterbox.server.service;

import at.simulevski.chatterbox.server.domain.ChannelDto;
import at.simulevski.chatterbox.server.domain.transactional.CreateChannelRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChannelService {
    ChannelDto createChannel(CreateChannelRequest request);

    void deleteChannel(String name);

    List<ChannelDto> getChannels();
}
