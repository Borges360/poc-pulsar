package br.com.pocpulsarproducer.producer.config;

import org.apache.pulsar.client.api.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class PulsarClientProducer {

    @Bean
    public Producer<String> client() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        return client.newProducer(Schema.STRING)
                .topic("usuario")
                .enableChunking(true)
                .enableBatching(true)
                .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                .sendTimeout(10, TimeUnit.SECONDS)
                .batcherBuilder(BatcherBuilder.KEY_BASED)
                .create();
    }

}
