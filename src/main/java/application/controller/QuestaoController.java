package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import application.record.QuestaoDTO;
import application.service.QuestaoService;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {
    @Autowired
    private QuestaoService questaoService;
    
    @GetMapping
    public Iterable<QuestaoDTO> list() {
        return questaoService.findAll();
    }
    
    @GetMapping("/{id}")
    public QuestaoDTO getById(@PathVariable Long id) {
        return questaoService.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestaoDTO insert(@RequestBody QuestaoDTO questao) {
        return questaoService.insert(questao);
    }
    
    @PutMapping("/{id}")
    public QuestaoDTO update(@PathVariable Long id, @RequestBody QuestaoDTO questao) {
        return questaoService.update(id, questao);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        questaoService.delete(id);
    }
}