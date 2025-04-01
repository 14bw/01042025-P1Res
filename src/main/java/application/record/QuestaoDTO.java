package application.record;

import application.model.Questao;

public record QuestaoDTO(Long id, String enunciado, Long categoriaId, String categoriaNome) {
    
    public QuestaoDTO(Questao entidade) {
        this(
            entidade.getId(), 
            entidade.getEnunciado(), 
            entidade.getCategoria() != null ? entidade.getCategoria().getId() : null,
            entidade.getCategoria() != null ? entidade.getCategoria().getNome() : null
        );
    }
}