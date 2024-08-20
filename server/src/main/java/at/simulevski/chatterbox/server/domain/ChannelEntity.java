package at.simulevski.chatterbox.server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "channels")
public class ChannelEntity {
    @Id
    private String id = UUID.randomUUID().toString();

    @Indexed(unique = true)
    private String name;

    private String description;
}
