package at.simulevski.chatterbox.server.controller;

import at.simulevski.chatterbox.server.domain.ChannelDto;
import at.simulevski.chatterbox.server.domain.transactional.CreateChannelRequest;
import at.simulevski.chatterbox.server.service.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/channel")
public class ChannelController {

    private final ChannelService channelService;

    @GetMapping("/")
    public ResponseEntity<List<ChannelDto>> getAllChannels() {
        return ResponseEntity.ok(channelService.getChannels());
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChannelDto> createChannel(@RequestBody CreateChannelRequest request) {
        return ResponseEntity.ok(channelService.createChannel(request));
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteChannel(@PathVariable String name) {
        channelService.deleteChannel(name);
    }
}
