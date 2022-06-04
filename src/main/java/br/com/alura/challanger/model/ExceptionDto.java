package br.com.alura.challanger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionDto{

    private int statusCode;
    private String mensagem;

    public ExceptionDto(){}
}
