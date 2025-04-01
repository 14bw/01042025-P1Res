package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import application.model.Opcao;
import application.record.OpcaoDTO;
import application.repository.OpcaoRepository;
import application.repository.QuestaoRepository;

@Service
public class OpcaoService {
    @Autowired
    private OpcaoRepository opcaoRepository;
    
    @Autowired
    private QuestaoRepository questaoRepository;
    
    public OpcaoDTO insert(OpcaoDTO dados) {
        Opcao opcao = new Opcao(dados);
        
        if (dados.questaoId() != null) {
            opcao.setQuestao(questaoRepository.findById(dados.questaoId())
                .orElseThrow(() -> new EntityNotFoundException("Questão não encontrada")));
        }
        
        return new OpcaoDTO(opcaoRepository.save(opcao));
    }
    
    public Iterable<OpcaoDTO> findAll() {
        return opcaoRepository.findAll().stream().map(OpcaoDTO::new).toList();
    }
    
    public OpcaoDTO findById(Long id) {
        return new OpcaoDTO(opcaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Opção não encontrada")));
    }
    
    public OpcaoDTO update(Long id, OpcaoDTO dados) {
        return opcaoRepository.findById(id)
                .map(opcao -> {
                    opcao.setDescricao(dados.descricao());
                    opcao.setCorreta(dados.correta());
                    
                    if (dados.questaoId() != null) {
                        opcao.setQuestao(questaoRepository.findById(dados.questaoId())
                            .orElseThrow(() -> new EntityNotFoundException("Questão não encontrada")));
                    }
                    
                    return new OpcaoDTO(opcaoRepository.save(opcao));
                })
                .orElseThrow(() -> new EntityNotFoundException("Opção não encontrada"));
    }
    
    public void delete(Long id) {
        opcaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Opção não encontrada"));
        opcaoRepository.deleteById(id);
    }
}