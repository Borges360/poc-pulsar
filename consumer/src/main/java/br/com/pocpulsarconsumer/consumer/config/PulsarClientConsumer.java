package br.com.pocpulsarconsumer.consumer.config;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarClientConsumer {

    @Bean
    public Consumer client() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        return client.newConsumer()
                .topic("usuario")
                .subscriptionName("poc-pulsar-consumer")
                .subscribe();
    }

}
