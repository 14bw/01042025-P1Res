package application.record;

import application.model.Opcao;

public record OpcaoDTO(Long id, String descricao, Boolean correta, Long questaoId, String questaoEnunciado) {
    
    public OpcaoDTO(Opcao entidade) {
        this(
            entidade.getId(), 
            entidade.getDescricao(), 
            entidade.getCorreta(),
            entidade.getQuestao() != null ? entidade.getQuestao().getId() : null,
            entidade.getQuestao() != null ? entidade.getQuestao().getEnunciado() : null
        );
    }
}