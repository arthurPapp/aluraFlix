package br.com.alura.challanger.entity;

import br.com.alura.challanger.model.VideoRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "video")
@Getter
@Setter
@AllArgsConstructor
public class VideoEntity implements Serializable {

    @Id
    private String id;

    private String titulo;
    private String descricao;
    private String url;
    private CategoriaEntity categoria;

    public VideoEntity(){}

    public VideoEntity(String titulo, String descricao, String url){
        this.titulo=titulo;
        this.descricao=descricao;
        this.url=url;
    }
    public VideoEntity(String titulo, String descricao, String url ,CategoriaEntity categoria){
        this.titulo=titulo;
        this.descricao=descricao;
        this.url=url;
        this.categoria=categoria;
    }

    public VideoEntity convertAtt(VideoRequest request, VideoEntity videoEntityBanco,CategoriaEntity categoria){

        videoEntityBanco.setUrl(request.getUrl());
        videoEntityBanco.setTitulo(request.getTitulo());
        videoEntityBanco.setDescricao(request.getDescricao());
        videoEntityBanco.setCategoria(categoria);
        return videoEntityBanco;
    }

}
