package user.service;

import user.Usuario;

import java.util.List;

public interface GenerateUsuarios {

    List<Usuario> generateBatchUser(int quantidade);

}
