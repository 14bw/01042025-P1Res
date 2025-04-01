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
import application.record.OpcaoDTO;
import application.service.OpcaoService;

@RestController
@RequestMapping("/opcoes")
public class OpcaoController {
    @Autowired
    private OpcaoService opcaoService;
    
    @GetMapping
    public Iterable<OpcaoDTO> list() {
        return opcaoService.findAll();
    }
    
    @GetMapping("/{id}")
    public OpcaoDTO getById(@PathVariable Long id) {
        return opcaoService.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OpcaoDTO insert(@RequestBody OpcaoDTO opcao) {
        return opcaoService.insert(opcao);
    }
    
    @PutMapping("/{id}")
    public OpcaoDTO update(@PathVariable Long id, @RequestBody OpcaoDTO opcao) {
        return opcaoService.update(id, opcao);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        opcaoService.delete(id);
    }
}