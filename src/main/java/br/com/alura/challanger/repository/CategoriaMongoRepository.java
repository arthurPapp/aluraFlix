package br.com.alura.challanger.repository;

import br.com.alura.challanger.entity.CategoriaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoriaMongoRepository extends MongoRepository<CategoriaEntity, String> {
}
