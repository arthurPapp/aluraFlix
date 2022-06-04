package br.com.alura.challanger.service;

import br.com.alura.challanger.entity.VideoEntity;
import br.com.alura.challanger.model.Video;
import br.com.alura.challanger.model.VideoRequest;
import br.com.alura.challanger.repository.VideoMongorepository;
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

    public Video inserirVideo(VideoRequest request){

        VideoEntity entity = new VideoEntity(request.getTitulo(),request.getDescricao(), request.getUrl() );
         entity=  videoMongorepository.save(entity);

         return new Video(entity.getId(),entity.getTitulo(),entity.getDescricao(),entity.getUrl());
    }

    public List<Video> getVideo(Pageable paginacao){
        Page<VideoEntity> videosEntities = videoMongorepository.findAll(paginacao);
        List<Video> response = new ArrayList<>();
        if(!videosEntities.getContent().isEmpty())
            videosEntities.forEach(videoEntity -> response.add(new Video(videoEntity.getId(), videoEntity.getTitulo(),videoEntity.getDescricao(),videoEntity.getUrl())));


        return response;
    }

    public Video getVideoId(String id){
        VideoEntity videosEntities = videoMongorepository.findById(id).orElseThrow(() -> new BadRequestException("Parametro não encontrado"));
        return new Video(videosEntities.getId(), videosEntities.getTitulo(),videosEntities.getDescricao(),videosEntities.getUrl());
    }

    public Video atualizaVideo(VideoRequest request){
        VideoEntity videosEntities = videoMongorepository.findById(request.getId()).orElseThrow(() -> new BadRequestException("Parametro não encontrado"));
        videosEntities.convertAtt(request,videosEntities);
        return new Video().convert(videoMongorepository.save(videosEntities));
    }

    public String deletarVideo(String id){
        VideoEntity videosEntities = videoMongorepository.findById(id).orElseThrow(() -> new BadRequestException("Parametro não encontrado"));
        videoMongorepository.deleteById(id);
        return "{\"Status\":\"DELETADO\"}";
    }
}
