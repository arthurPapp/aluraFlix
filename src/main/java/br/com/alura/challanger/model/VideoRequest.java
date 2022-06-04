package br.com.alura.challanger.model;

import br.com.alura.challanger.validator.group.PutChecks;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoRequest {

    @NotBlank(message = "Id do video não pode ser nulo", groups = { PutChecks.class })
    private String id;

    @NotBlank(message = "titulo do video não pode ser nulo")
    @Size(max= 200, message = "titulo não pode ultrapassar 200 caracteres")
    private String titulo;

    @NotBlank(message = "descricao do video não pode ser nulo")
    @Size(max= 250, message = "descricao não pode ultrapassar 250 caracteres")
    private String descricao;

    @NotBlank(message = "url do video não pode ser nulo")
    @Size(max= 100, message = "url não pode ultrapassar 100 caracteres")
    //@Pattern(regexp = "([a-zA-Z]{3,})://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?")
    @URL(message = "url invalida")
    private String url;

    public VideoRequest(){}

    public VideoRequest(String titulo, String descricao, String url){}
}
