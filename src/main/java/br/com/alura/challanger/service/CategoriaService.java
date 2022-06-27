package br.com.alura.challanger.service;

import br.com.alura.challanger.entity.CategoriaEntity;
import br.com.alura.challanger.entity.VideoEntity;
import br.com.alura.challanger.model.Categoria;
import br.com.alura.challanger.model.CategoriaRequest;
import br.com.alura.challanger.model.Video;
import br.com.alura.challanger.repository.CategoriaMongoRepository;
import br.com.alura.challanger.repository.VideoMongorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaMongoRepository categoriaMongoRepository;

    @Autowired
    VideoMongorepository videoMongorepository;

    public Categoria inserirCategoria(CategoriaRequest categoriaRequest) {
        try{
            CategoriaEntity entity = categoriaMongoRepository.save(new CategoriaEntity(categoriaRequest.getTitulo(),categoriaRequest.getCor()));

            return new Categoria().convert(entity);
        }catch (Exception e){
            throw  e;
        }
    }

    public Page<Categoria> getCategorias(Pageable paginacao) {
        try{
            Page<CategoriaEntity> entity = categoriaMongoRepository.findAll(paginacao);

            return new Categoria().convertPage(entity);


        }catch (Exception e){
            throw  e;
        }
    }
    public Categoria getCategoria(String id) {
        CategoriaEntity entity = categoriaMongoRepository.findById(id).orElseThrow(() -> new BadRequestException("categoria não encontrada"));
        return new Categoria(entity.getId(), entity.getTitulo(), entity.getCor());
    }

    public Categoria atualizaCategoria(CategoriaRequest categoriaRequest) {
        CategoriaEntity entity = categoriaMongoRepository.findById(categoriaRequest.getId()).orElseThrow(() -> new BadRequestException("Parametro não encontrado"));
        entity.convert(entity, categoriaRequest);
        return new Categoria().convert(categoriaMongoRepository.save(entity));
    }

    public String deletarCategoria(String id) {
        CategoriaEntity entity = categoriaMongoRepository.findById(id).orElseThrow(() -> new BadRequestException("Parametro não encontrado"));
        categoriaMongoRepository.deleteById(id);
        return "{\"Status\":\"DELETADO\"}";
    }

    public Page<Video> getVideoPorCategoria(Pageable paginacao, String id) {
        Page<VideoEntity> videosEntities = videoMongorepository.findVideoCategoriaId(paginacao, id);
        if(!videosEntities.getContent().isEmpty())
            return new Video().convertPage(videosEntities);

        return null;
    }
}
