package br.com.pocpulsarconsumer.consumer.controller;


import br.com.pocpulsarconsumer.consumer.config.PulsarClientConsumer;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class UsuarioConsumer {

    @Autowired
    private PulsarClientConsumer pulsarClientConsumer;

    @Bean
    public void consumer() {
        Thread thread = new Thread(() -> {
            while (true) {
                // Wait for a message
                CompletableFuture<Message> msg = null;
                try {
                    msg = pulsarClientConsumer.client().receiveAsync();
                } catch (PulsarClientException e) {
                    e.printStackTrace();
                }

                try {
                    // Do something with the message
                    System.out.println("Message received: " + new String(msg.get().getData()));

                    // Acknowledge the message so that it can be deleted by the message broker
                    pulsarClientConsumer.client().acknowledge(msg.get());
                } catch (Exception e) {
                    // Message failed to process, redeliver later
                    try {
                        pulsarClientConsumer.client().negativeAcknowledge(msg.get());
                    }  catch (ExecutionException | InterruptedException | PulsarClientException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

}
