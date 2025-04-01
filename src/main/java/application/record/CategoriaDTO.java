package application.record;

import application.model.Categoria;

public record CategoriaDTO(Long id, String nome) {
    public CategoriaDTO(Categoria entidade) {
        this(entidade.getId(), entidade.getNome());
    }
}