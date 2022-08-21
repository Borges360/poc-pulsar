package br.com.pocpulsarconsumer.consumer.controller;


import br.com.pocpulsarconsumer.consumer.config.PulsarClientConsumer;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioConsumer {

    @Autowired
    private PulsarClientConsumer pulsarClientConsumer;

    @Bean
    public void consumer() throws PulsarClientException {
        Thread thread = new Thread(() -> {
            while (true) {
                // Wait for a message
                Message msg = null;
                try {
                    msg = pulsarClientConsumer.client().receive();
                } catch (PulsarClientException e) {
                    e.printStackTrace();
                }

                try {
                    // Do something with the message
                    System.out.println("Message received: " + new String(msg.getData()));

                    // Acknowledge the message so that it can be deleted by the message broker
                    pulsarClientConsumer.client().acknowledge(msg);
                } catch (Exception e) {
                    // Message failed to process, redeliver later
                    try {
                        pulsarClientConsumer.client().negativeAcknowledge(msg);
                    } catch (PulsarClientException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

}
