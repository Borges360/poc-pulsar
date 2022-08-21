package br.com.pocpulsarproducer.producer.service;

import org.apache.pulsar.client.api.PulsarClientException;

public interface UsuariosProducer {

    void start(int quantidade) throws PulsarClientException;

}
