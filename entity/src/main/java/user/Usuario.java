package user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
public class Usuario {

    private String cpf;
    private String nome;
    private String telefone;
    private Date dataNascimento;

}
