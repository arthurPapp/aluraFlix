package br.com.alura.challanger.componet;


import br.com.alura.challanger.entity.VideoEntity;
import br.com.alura.challanger.repository.VideoMongorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.ws.rs.BadRequestException;

public class VideoComponent {

    @Autowired
    VideoMongorepository videoMongorepository;

    public VideoEntity findVideoPorId(String id){
        return videoMongorepository.findById(id).orElseThrow(() -> new BadRequestException("Parametro não encontrado"));
    }

    public Page<VideoEntity> getVideos(Pageable paginacao){
        return videoMongorepository.findAll(paginacao);
    }
}
