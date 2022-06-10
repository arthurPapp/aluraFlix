package br.com.alura.challanger.model;

import br.com.alura.challanger.entity.CategoriaEntity;
import br.com.alura.challanger.entity.VideoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Video {

    private String id;
    private String titulo;
    private String descricao;
    private String url;
    private Categoria categoria;

    public Video(){}

    public Video(String id, String titulo, String descricao, String url) {
        this.id=id;
        this.titulo=titulo;
        this.descricao=descricao;
        this.url=url;
    }


    public Video convert(VideoEntity entrada){
        return new Video(entrada.getId(), entrada.getTitulo(), entrada.getDescricao(), entrada.getUrl());
    }
}
