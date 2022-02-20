package com.pingr.Connections.application;

import com.pingr.Connections.core.Account;
import com.pingr.Connections.core.Friendship;
import com.pingr.Connections.core.events.AccountCreatedEvent;
import com.pingr.Connections.core.events.AccountRemovedEvent;
import com.pingr.Connections.core.events.FriendshipCreatedEvent;
import com.pingr.Connections.core.events.FriendshipRemovedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(
            containerFactory = "accountCreatedEventKafkaListenerContainerFactory",
            topics = "${topics.acc_created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleAccountCreation(AccountCreatedEvent event) {
        Account account = event.extract();
        System.out.println(account);
    }

    @KafkaListener(
            containerFactory = "accountRemovedEventKafkaListenerContainerFactory",
            topics = "${topics.acc_removed}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleAccountDeletion(AccountRemovedEvent event) {
        Account account = event.extract();
        System.out.println(account);
    }

    @KafkaListener(
            containerFactory = "friendshipCreatedEventKafkaListenerContainerFactory",
            topics = "${topics.friendship_created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleFriendshipCreation(FriendshipCreatedEvent event) {
        Friendship friendship = event.extract();
        System.out.println(friendship);
    }

    @KafkaListener(
            containerFactory = "friendshipRemovedEventKafkaListenerContainerFactory",
            topics = "${topics.friendship_removed}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleFriendshipDeletion(FriendshipRemovedEvent event) {
        Friendship friendship = event.extract();
        System.out.println(friendship);
    }
}
