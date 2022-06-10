package br.com.alura.challanger.model;

import br.com.alura.challanger.validator.group.PutChecks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaRequest {


    @NotBlank(message = "Id do video não pode ser nulo", groups = { PutChecks.class })
    private String id;

    @NotBlank(message = "titulo da categoria pode ser nulo")
    @Size(max= 200, message = "titulo não pode ultrapassar 200 caracteres")
    private String titulo;

    @NotBlank(message = "cor da categoria não pode ser nulo")
    @Size(max= 12, message = "url não pode ultrapassar 100 caracteres")
    private String cor;

    public CategoriaRequest(){}

}
