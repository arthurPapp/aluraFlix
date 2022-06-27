package br.com.alura.challanger.entity;

import org.springframework.security.core.GrantedAuthority;

public class Perfil implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return nome;
    }

}

