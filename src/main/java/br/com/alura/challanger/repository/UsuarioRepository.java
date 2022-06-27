package br.com.alura.challanger.repository;

import br.com.alura.challanger.entity.CategoriaEntity;
import br.com.alura.challanger.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    @Query("{username : '?0'}")
    Optional<Usuario> findByUsername(String userName);
}
