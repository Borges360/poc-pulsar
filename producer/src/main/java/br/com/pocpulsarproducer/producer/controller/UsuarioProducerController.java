package br.com.pocpulsarproducer.producer.controller;

import br.com.pocpulsarproducer.producer.service.UsuariosProducer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioProducerController {

    private final UsuariosProducer usuariosProducer;

    @Autowired
    public UsuarioProducerController(UsuariosProducer usuariosProducer) {
        this.usuariosProducer = usuariosProducer;
    }

    @GetMapping("gerar/usuario/{quantidade}")
    public void generateUsuarios(@PathVariable("quantidade") int quantidade){

        try {
            usuariosProducer.start(quantidade);
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }

    }


}
