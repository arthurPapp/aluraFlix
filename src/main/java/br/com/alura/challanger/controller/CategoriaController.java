package br.com.alura.challanger.controller;

import br.com.alura.challanger.constant.CategoriaConst;
import br.com.alura.challanger.model.Categoria;
import br.com.alura.challanger.model.CategoriaRequest;
import br.com.alura.challanger.model.Video;
import br.com.alura.challanger.model.VideoRequest;
import br.com.alura.challanger.service.CategoriaService;
import br.com.alura.challanger.validator.group.PutChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;
import javax.ws.rs.BadRequestException;
import java.util.List;

@RestController
@Validated
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> inserirCategoria(@Valid @RequestBody CategoriaRequest categoriaRequest){
        return new ResponseEntity<Categoria>(categoriaService.inserirCategoria(categoriaRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Categoria>> listarCategorias(@RequestParam("page") @Min(value = 1, message = "O valor informado deve ser maior ou igual a 1") Integer page,
                                                    @RequestParam  @Min(value = 1, message = "O valor informado deve ser maior ou igual a 1") Integer qtd){
        Pageable paginacao = PageRequest.of(page-1,qtd);
        return new ResponseEntity<Page<Categoria>>(categoriaService.getCategorias(paginacao), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria( @PathVariable("id") @NotBlank(message = "id não pode ser nulo!") String id){
        return new ResponseEntity<Categoria>(categoriaService.getCategoria(id), HttpStatus.OK);
    }

    @PutMapping
    public  ResponseEntity<Categoria> atualizarCategoria(@Validated({ Default.class, PutChecks.class }) @RequestBody CategoriaRequest categoriaRequest){
        return new ResponseEntity<Categoria>(categoriaService.atualizaCategoria(categoriaRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletarCategoria(@PathVariable("id") @NotBlank(message = "id não pode ser nulo!") String id){
        if(CategoriaConst.ID_CATEGORIA.equalsIgnoreCase(id))
            return new ResponseEntity<String>("{\"Erro\":\"Acesso negado para categoria\"}", HttpStatus.FORBIDDEN);
        return new ResponseEntity<String>(categoriaService.deletarCategoria(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<Page<Video>> getVideoPorCategoria( @PathVariable("id") @NotBlank(message = "id não pode ser nulo!") String id,
                                                             @RequestParam("page") @Min(value = 1, message = "O valor informado deve ser maior ou igual a 1") Integer page,
                                                             @RequestParam  @Min(value = 1, message = "O valor informado deve ser maior ou igual a 1") Integer qtd){
        Pageable paginacao = PageRequest.of(page-1,qtd);
        return new ResponseEntity<Page<Video>>(categoriaService.getVideoPorCategoria(paginacao, id), HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<Page<Video>> getVideoPorCategoria( @RequestParam("page") @Min(value = 1, message = "O valor informado deve ser maior ou igual a 1") Integer page,
                                                             @RequestParam  @Min(value = 1, message = "O valor informado deve ser maior ou igual a 1") Integer qtd){
        Pageable paginacao = PageRequest.of(page-1,qtd);
        return new ResponseEntity<Page<Video>>(categoriaService.getVideoPorCategoria(paginacao,CategoriaConst.CATEGORIA_FREE), HttpStatus.OK);
    }
}
