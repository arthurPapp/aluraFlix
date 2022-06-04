package br.com.alura.challanger.model;

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

    public Video(){}


    public Video convert(VideoEntity entrada){
        return new Video(entrada.getId(), entrada.getTitulo(), entrada.getDescricao(), entrada.getUrl());
    }
}
