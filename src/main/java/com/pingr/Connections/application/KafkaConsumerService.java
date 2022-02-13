package com.pingr.Connections.application;

import com.pingr.Connections.core.Account;
import com.pingr.Connections.core.events.AccountCreatedEvent;
import com.pingr.Connections.core.events.AccountRemovedEvent;
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
}
