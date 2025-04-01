package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import application.model.Questao;
import application.record.QuestaoDTO;
import application.repository.QuestaoRepository;
import application.repository.CategoriaRepository;

@Service
public class QuestaoService {
    @Autowired
    private QuestaoRepository questaoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public QuestaoDTO insert(QuestaoDTO dados) {
        Questao questao = new Questao(dados);
        
        if (dados.categoriaId() != null) {
            questao.setCategoria(categoriaRepository.findById(dados.categoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada")));
        }
        
        return new QuestaoDTO(questaoRepository.save(questao));
    }
    
    public Iterable<QuestaoDTO> findAll() {
        return questaoRepository.findAll().stream().map(QuestaoDTO::new).toList();
    }
    
    public QuestaoDTO findById(Long id) {
        return new QuestaoDTO(questaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Questão não encontrada")));
    }
    
    public QuestaoDTO update(Long id, QuestaoDTO dados) {
        return questaoRepository.findById(id)
                .map(questao -> {
                    questao.setEnunciado(dados.enunciado());
                    
                    if (dados.categoriaId() != null) {
                        questao.setCategoria(categoriaRepository.findById(dados.categoriaId())
                            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada")));
                    }
                    
                    return new QuestaoDTO(questaoRepository.save(questao));
                })
                .orElseThrow(() -> new EntityNotFoundException("Questão não encontrada"));
    }
    
    public void delete(Long id) {
        questaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Questão não encontrada"));
        questaoRepository.deleteById(id);
    }
}