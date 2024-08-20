package at.simulevski.chatterbox.server.persistence;

import at.simulevski.chatterbox.server.domain.ChannelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends MongoRepository<ChannelEntity, String> {
    void deleteByName(String name);

    Optional<ChannelEntity> findByName(String name);
}
