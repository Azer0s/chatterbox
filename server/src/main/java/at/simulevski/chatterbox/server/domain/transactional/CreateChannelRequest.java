package at.simulevski.chatterbox.server.domain.transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateChannelRequest {
    private String name;
    private String description;
}
