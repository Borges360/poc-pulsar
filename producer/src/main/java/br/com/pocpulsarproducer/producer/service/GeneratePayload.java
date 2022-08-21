package br.com.pocpulsarproducer.producer.service;

import br.com.pocpulsarproducer.producer.repository.NamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.Usuario;

import java.util.*;

@Service
public class GeneratePayload {

    private final NamesRepository namesRepository;

    @Autowired
    public GeneratePayload(NamesRepository namesRepository) {
        this.namesRepository = namesRepository;
    }

    public List<Usuario> generateBatchUser(int quantidade){

        List<Usuario> usuarios = new ArrayList<>(quantidade);
        namesRepository.getNames(quantidade).forEach(
                nome -> usuarios.add(generateUsuario(nome))
        );

        return usuarios;
    }

    private Usuario generateUsuario(String name){
        Usuario user = new Usuario();
        Random random = new Random();
        user.setCpf(String.valueOf(random.nextLong(99999999999L - 10000000000L)));
        user.setNome(name);
        user.setTelefone(String.valueOf(random.nextLong(99999999999L - 10000000000L)));
        int dia = random.nextInt(31);
        int mes = random.nextInt(12);
        int ano = random.nextInt(2022-1940);
        Date dataDeNascimento = new GregorianCalendar(ano, mes - 1, dia).getTime();
        user.setDataNascimento(dataDeNascimento);
        return user;
    }

}
