package br.com.pocpulsarproducer.producer.service.impl;

import br.com.pocpulsarproducer.producer.config.PulsarClientProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import user.repository.NamesRepository;
import user.service.GenerateUsuarios;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.springframework.stereotype.Service;
import br.com.pocpulsarproducer.producer.service.UsuariosProducer;
import user.service.impl.GenerateUsuariosImpl;

@Service
@Log4j2
public class UsuariosProducerImpl implements UsuariosProducer {

    private final GenerateUsuarios generateUsuarios;

    @Autowired
    private PulsarClientProducer pulsarClientProducer;

    @Autowired
    public UsuariosProducerImpl() {
        this.generateUsuarios = new GenerateUsuariosImpl(new NamesRepository());
    }

    public void start(int quantidade) {

        generateUsuarios.generateBatchUser(quantidade).forEach(usuario -> {
            try {
                pulsarClientProducer.client().sendAsync(usuario.toString()).thenAccept(msgId -> {
                   log.info("Message with ID " + msgId + " successfully sent");
                });
            } catch (PulsarClientException e) {
                e.printStackTrace();
            }
        });

    }

}
