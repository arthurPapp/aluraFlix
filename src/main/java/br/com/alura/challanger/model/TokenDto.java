package br.com.alura.challanger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TokenDto {

    private String token;
    private String tipo;

    public TokenDto() {}

}
