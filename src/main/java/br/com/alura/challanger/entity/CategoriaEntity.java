package br.com.alura.challanger.entity;

import br.com.alura.challanger.model.CategoriaRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "categoria")
@Getter
@Setter
@AllArgsConstructor
public class CategoriaEntity implements Serializable {

    private String id;
    private String titulo;
    private String cor;

    public CategoriaEntity(){}

    public CategoriaEntity(String titulo, String cor){
        this.titulo=titulo;
        this.cor=cor;
    }

    public CategoriaEntity convert(CategoriaEntity entity, CategoriaRequest entrada){
        entity.setTitulo(entrada.getTitulo());
        entity.setCor(entrada.getCor());
        return entity;
    }
}
