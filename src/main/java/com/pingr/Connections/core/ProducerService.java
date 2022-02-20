package com.pingr.Connections.core;

import com.pingr.Connections.core.events.FriendshipCreatedEvent;
import com.pingr.Connections.core.events.FriendshipRemovedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// forma de se comunicar com o Kakfa
// eventos que vamos publicar
// - friendshipCreated
// - friendshipDeleted

@Service
public class ProducerService {

    @Value(value = "${topics.friendship_created}")
    private String friendshipCreatedTopic;

    @Value(value = "${topics.friendship_deleted}")
    private String friendshipRemovedTopic;

    @Autowired // injeção de dependências
    private KafkaTemplate<String, Object> template;

    public void emitFriendshipCreatedEvent(Friendship friendship) {
        this.template.send(
                this.friendshipCreatedTopic,
                FriendshipCreatedEvent.of(friendship));
    }

    public void emitFriendshipRemovedEvent(Friendship friendship) {
        this.template.send(
                this.friendshipRemovedTopic,
                FriendshipRemovedEvent.of(friendship));
    }
}
