package br.com.alura.challanger.repository;

import br.com.alura.challanger.entity.VideoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface VideoMongorepository extends MongoRepository<VideoEntity, String> {

}
