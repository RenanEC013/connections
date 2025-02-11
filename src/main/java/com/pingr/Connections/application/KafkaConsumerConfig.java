package com.pingr.Connections.application;

import com.pingr.Connections.core.Account;
import com.pingr.Connections.core.events.AccountCreatedEvent;
import com.pingr.Connections.core.events.AccountRemovedEvent;
import com.pingr.Connections.core.events.AccountUpdatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return props;
    }

    @Bean
    public ConsumerFactory<String, Account> consumerFactory() {
        JsonDeserializer<Account> jsonDeserializer = new JsonDeserializer<>(Account.class);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                jsonDeserializer
        );
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Account>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Account> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }


    // =======================


    public ConsumerFactory<String, AccountCreatedEvent> accountCreatedEventConsumerFactory() {
        JsonDeserializer<AccountCreatedEvent> jsonDeserializer = new JsonDeserializer<>(AccountCreatedEvent.class);
        jsonDeserializer.setUseTypeMapperForKey(true);
        jsonDeserializer.addTrustedPackages("*");   // com.pingr.Accounts.Accounts.Account.java
                                                    // com.pingr.Connection.core.Account.java

        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                jsonDeserializer
        );
    }

    //                                                                          vamos lembrar deste nome     |
    @Bean  //                                                                                                v
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, AccountCreatedEvent>> accountCreatedEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AccountCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(accountCreatedEventConsumerFactory());

        return factory;
    }

    public ConsumerFactory<String, AccountUpdatedEvent> accountUpdatedEventConsumerFactory() {
        JsonDeserializer<AccountUpdatedEvent> jsonDeserializer = new JsonDeserializer<>(AccountUpdatedEvent.class);
        jsonDeserializer.setUseTypeMapperForKey(true);
        jsonDeserializer.addTrustedPackages("*");   // com.pingr.Accounts.Accounts.Account.java
        // com.pingr.Connection.core.Account.java

        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                jsonDeserializer
        );
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, AccountUpdatedEvent>> accountUpdatedEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AccountUpdatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(accountUpdatedEventConsumerFactory());

        return factory;
    }


    // ==========================

    public ConsumerFactory<String, AccountRemovedEvent> accountRemovedEventConsumerFactory() {
        JsonDeserializer<AccountRemovedEvent> jsonDeserializer = new JsonDeserializer<>(AccountRemovedEvent.class);
        jsonDeserializer.setUseTypeMapperForKey(true);
        jsonDeserializer.addTrustedPackages("*");   // com.pingr.Accounts.Accounts.Account.java
        // com.pingr.Connection.core.Account.java

        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                jsonDeserializer
        );
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, AccountRemovedEvent>> accountRemovedEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AccountRemovedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(accountRemovedEventConsumerFactory());

        return factory;
    }


}
