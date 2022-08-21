package br.com.pocpulsarproducer.producer.config;

import org.apache.pulsar.client.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarClientProducer {

    @Bean
    public Producer<String> client() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        return client.newProducer(Schema.STRING)
                .topic("usuario")
                .create();
    }

}
