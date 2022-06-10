package br.com.alura.challanger.service;

import br.com.alura.challanger.entity.CategoriaEntity;
import br.com.alura.challanger.entity.VideoEntity;
import br.com.alura.challanger.model.Categoria;
import br.com.alura.challanger.model.CategoriaRequest;
import br.com.alura.challanger.model.Video;
import br.com.alura.challanger.repository.CategoriaMongoRepository;
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

    public Categoria inserirCategoria(CategoriaRequest categoriaRequest) {
        try{
            CategoriaEntity entity = categoriaMongoRepository.save(new CategoriaEntity(categoriaRequest.getTitulo(),categoriaRequest.getCor()));

            return new Categoria().convert(entity);
        }catch (Exception e){
            throw  e;
        }
    }

    public List<Categoria> getCategorias(Pageable paginacao) {
        try{
            Page<CategoriaEntity> entity = categoriaMongoRepository.findAll(paginacao);
            List<Categoria> response = new ArrayList<>();
            if(!entity.getContent().isEmpty())
                entity.forEach(categoriaEntity -> response.add(new Categoria(categoriaEntity.getId(),categoriaEntity.getTitulo(), categoriaEntity.getCor())) );

            return response;
        }catch (Exception e){
            throw  e;
        }
    }
    public Categoria getCategoria(String id) {
        CategoriaEntity entity = categoriaMongoRepository.findById(id).orElseThrow(() -> new BadRequestException("categoria não encontrada"));
        return new Categoria(entity.getId(), entity.getTitulo(), entity.getCor());
    }

    public Categoria atualizaVideo(CategoriaRequest categoriaRequest) {
        CategoriaEntity entity = categoriaMongoRepository.findById(categoriaRequest.getId()).orElseThrow(() -> new BadRequestException("Parametro não encontrado"));
        entity.convert(entity, categoriaRequest);
        return new Categoria().convert(categoriaMongoRepository.save(entity));
    }
}
