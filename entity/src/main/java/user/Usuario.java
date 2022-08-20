package user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Usuario {

    private String cpf;
    private String nome;
    private String telefone;
    private Date dataNascimento;

}
