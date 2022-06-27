package br.com.alura.challanger.repository;

import br.com.alura.challanger.entity.VideoEntity;
import br.com.alura.challanger.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface VideoMongorepository extends MongoRepository<VideoEntity, String> {

    @Query("{'categoria.id' : ?0}")
    Page<VideoEntity> findVideoCategoriaId(Pageable pageable, String id);
}
