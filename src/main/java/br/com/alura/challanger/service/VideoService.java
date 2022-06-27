package br.com.alura.challanger.service;

import br.com.alura.challanger.entity.CategoriaEntity;
import br.com.alura.challanger.entity.VideoEntity;
import br.com.alura.challanger.model.Categoria;
import br.com.alura.challanger.model.Video;
import br.com.alura.challanger.model.VideoRequest;
import br.com.alura.challanger.repository.CategoriaMongoRepository;
import br.com.alura.challanger.repository.VideoMongorepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoMongorepository videoMongorepository;

    @Autowired
    CategoriaMongoRepository categoriaMongoRepository;

    public Video inserirVideo(VideoRequest request){
        try{
            CategoriaEntity categoria = categoriaMongoRepository.findById(request.getIdCategoria()).orElseThrow(() -> new BadRequestException("id categoria não encontrado!"));

            VideoEntity entity = new VideoEntity(request.getTitulo(),request.getDescricao(), request.getUrl(), categoria );
            entity=  videoMongorepository.save(entity);

            return new Video(entity.getId(),entity.getTitulo(),entity.getDescricao(),entity.getUrl(), new Categoria().convert(categoria));
        }catch (Exception e){
            throw e;
        }

    }

    public Page<Video> getVideo(Pageable paginacao){
        Page<VideoEntity> videosEntities = videoMongorepository.findAll(paginacao);
        if(!videosEntities.getContent().isEmpty())
           return new Video().convertPage(videosEntities);


        return null;
    }

    public Video getVideoId(String id){
        VideoEntity videosEntities = videoMongorepository.findById(id).orElseThrow(() -> new BadRequestException("Parametro não encontrado"));
        return new Video(videosEntities.getId(), videosEntities.getTitulo(),videosEntities.getDescricao(),videosEntities.getUrl());
    }



    public Video atualizaVideo(VideoRequest request){
        VideoEntity videosEntities = videoMongorepository.findById(request.getId()).orElseThrow(() -> new BadRequestException("Parametro não encontrado"));
        CategoriaEntity categoria = categoriaMongoRepository.findById(request.getIdCategoria()).orElseThrow(() -> new BadRequestException("id categoria não encontrado!"));
        videosEntities.convertAtt(request,videosEntities, categoria);
        return new Video().convert(videoMongorepository.save(videosEntities));
    }

    public String deletarVideo(String id){
        VideoEntity videosEntities = videoMongorepository.findById(id).orElseThrow(() -> new BadRequestException("Parametro não encontrado"));
        videoMongorepository.deleteById(id);
        return "{\"Status\":\"DELETADO\"}";
    }



}
