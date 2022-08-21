package user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user.Usuario;
import user.repository.NamesRepository;
import user.service.GenerateUsuarios;

import java.util.*;

public class GenerateUsuariosImpl implements GenerateUsuarios {

    private final NamesRepository namesRepository;

    public GenerateUsuariosImpl(NamesRepository namesRepository) {
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
        user.setCpf(String.valueOf(random.nextLong()));
        user.setNome(name);
        user.setTelefone(String.valueOf(random.nextLong()));
        int dia = random.nextInt(31);
        int mes = random.nextInt(12);
        int ano = random.nextInt(2022-1940);
        Date dataDeNascimento = new GregorianCalendar(ano, mes - 1, dia).getTime();
        user.setDataNascimento(dataDeNascimento);
        return user;
    }

}
