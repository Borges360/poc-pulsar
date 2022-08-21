package br.com.pocpulsarconsumer.consumer.controller;


import br.com.pocpulsarconsumer.consumer.config.PulsarClientConsumer;
import lombok.extern.log4j.Log4j2;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Messages;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
public class UsuarioConsumer {

    @Autowired
    private PulsarClientConsumer pulsarClientConsumer;

    @Bean
    public void consumer() {
        Thread thread = new Thread(() -> {
            while (true) {
                // Wait for a message
                Messages<Message> msgs = null;
                try {
                    msgs = pulsarClientConsumer.client().batchReceive();
                    for(Message message : msgs){
                        log.info("Message received: " + new String(message.getData()));
                    }
                } catch (PulsarClientException e) {
                    e.printStackTrace();
                }

                try {
                    pulsarClientConsumer.client().acknowledge(msgs);
                } catch (Exception e) {
                    // Message failed to process, redeliver later
                    try {
                        pulsarClientConsumer.client().negativeAcknowledge(msgs);
                    }  catch (PulsarClientException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

}
