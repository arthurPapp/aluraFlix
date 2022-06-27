package br.com.alura.challanger.model;

import br.com.alura.challanger.entity.CategoriaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
public class Categoria {

    private String id;
    private String titulo;
    private String cor;

    public Categoria(){}

    public Categoria( String titulo, String cor){
        this.titulo=titulo;
        this.cor=cor;
    }

    public Categoria(CategoriaEntity categoriaEntity) {
        this.id=categoriaEntity.getId();
        this.titulo=categoriaEntity.getTitulo();
        this.cor=categoriaEntity.getCor();
    }

    public Categoria convert(CategoriaEntity entrada){
        return new Categoria(entrada.getId(),entrada.getTitulo(),entrada.getCor());
    }

    public static Page<Categoria> convertPage(Page<CategoriaEntity> categorias){
        return categorias.map(Categoria::new);
    }

}
