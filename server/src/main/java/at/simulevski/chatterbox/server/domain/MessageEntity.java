package at.simulevski.chatterbox.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "messages")
public class MessageEntity {
    @Id
    private String id = UUID.randomUUID().toString();

    @DBRef
    private UserEntity sender;

    @DBRef
    private ChannelEntity channel;

    private Date sentAt;
}
