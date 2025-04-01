package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import application.model.Categoria;
import application.record.CategoriaDTO;
import application.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public CategoriaDTO insert(CategoriaDTO dados) {
        return new CategoriaDTO(categoriaRepository.save(new Categoria(dados)));
    }
    
    public Iterable<CategoriaDTO> findAll() {
        return categoriaRepository.findAll().stream().map(CategoriaDTO::new).toList();
    }
    
    public CategoriaDTO findById(Long id) {
        return new CategoriaDTO(categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada")));
    }
    
    public CategoriaDTO update(Long id, CategoriaDTO dados) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNome(dados.nome());
                    return new CategoriaDTO(categoriaRepository.save(categoria));
                })
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
    }
    
    public void delete(Long id) {
        categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        categoriaRepository.deleteById(id);
    }
}