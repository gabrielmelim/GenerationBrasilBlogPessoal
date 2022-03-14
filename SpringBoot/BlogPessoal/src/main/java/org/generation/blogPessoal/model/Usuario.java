package org.generation.blogPessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    // System generated
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idUsuario;

    // User Generated
    private @NotBlank String nome;
    private @NotBlank @CPF String cpf;
    private @NotBlank @Email String email;
    private @NotBlank String senha;

    // Relationship
    @OneToMany(mappedBy = "criador", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"criador"})
    private List<Postagem> minhasPostagens = new ArrayList<>();

    // Constructor
    public Usuario() {super();}

    public Usuario(String nome, String cpf, String email, String senha) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(Long idUsuario, String nome, String cpf, String email, String senha) {
        super();
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    // Getter and Setter
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public List<Postagem> getMinhasPostagens() {
        return minhasPostagens;
    }

    public void setMinhasPostagens(List<Postagem> minhasPostagens) {
        this.minhasPostagens = minhasPostagens;
    }
}
