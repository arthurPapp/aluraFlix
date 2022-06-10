package br.com.alura.challanger.model;

import br.com.alura.challanger.entity.CategoriaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Categoria {

    private String id;
    private String titulo;
    private String cor;

    public Categoria(){}

    public Categoria( String titulo, String cor){
        this.titulo=titulo;
        this.cor=cor;
    }

    public Categoria convert(CategoriaEntity entrada){
        return new Categoria(entrada.getId(),entrada.getTitulo(),entrada.getCor());
    }
}
