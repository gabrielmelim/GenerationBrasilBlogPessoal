package org.generation.blogPessoal.dtos;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Interface responsible for the validation register of the user.
 *
 * @author Melim
 * @since 13/03/2022
 * @version 1.0
 */

public class UserRegisterDTO {

    private @NotBlank String nome;
    private @NotBlank @CPF String cpf;
    private @NotBlank @Email String email;
    private @NotBlank String senha;

    public UserRegisterDTO() {
        super();
    }

    public UserRegisterDTO(String nome, String cpf, String email,String senha) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
