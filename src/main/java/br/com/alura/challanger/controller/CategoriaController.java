package br.com.alura.challanger.controller;

import br.com.alura.challanger.model.Categoria;
import br.com.alura.challanger.model.CategoriaRequest;
import br.com.alura.challanger.model.Video;
import br.com.alura.challanger.model.VideoRequest;
import br.com.alura.challanger.service.CategoriaService;
import br.com.alura.challanger.validator.group.PutChecks;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Categoria>> listarCategorias(@RequestParam("page") @Min(value = 1, message = "O valor informado deve ser maior ou igual a 1") Integer page,
                                                    @RequestParam  @Min(value = 1, message = "O valor informado deve ser maior ou igual a 1") Integer qtd){
        Pageable paginacao = PageRequest.of(page-1,qtd);
        return new ResponseEntity<List<Categoria>>(categoriaService.getCategorias(paginacao), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria( @PathVariable("id") @NotBlank(message = "id não pode ser nulo!") String id){
        return new ResponseEntity<Categoria>(categoriaService.getCategoria(id), HttpStatus.OK);
    }

    @PutMapping
    public  ResponseEntity<Categoria> atualizarCategoria(@Validated({ Default.class, PutChecks.class }) @RequestBody CategoriaRequest categoriaRequest){
        return new ResponseEntity<Categoria>(categoriaService.atualizaVideo(categoriaRequest), HttpStatus.OK);
    }
}
