package br.com.pocpulsarconsumer.consumer.config;

import org.apache.pulsar.client.api.BatchReceivePolicy;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

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
                .batchReceivePolicy(BatchReceivePolicy.builder()
                        .maxNumMessages(100)
                        .maxNumBytes(1024 * 1024)
                        .timeout(200, TimeUnit.MILLISECONDS)
                        .build())
                .subscribe();
    }

}
