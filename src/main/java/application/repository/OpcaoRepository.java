package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import application.model.Opcao;

public interface OpcaoRepository extends JpaRepository<Opcao, Long> {
    
}