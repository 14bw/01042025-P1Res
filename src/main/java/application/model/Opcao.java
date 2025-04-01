package application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import application.record.OpcaoDTO;

@Entity
@Table(name = "opcoes")
@Getter
@Setter
@NoArgsConstructor
public class Opcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 255, nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private Boolean correta;
    
    @ManyToOne
    @JoinColumn(name = "questao_id")
    private Questao questao;
    
    public Opcao(OpcaoDTO dto) {
        this.id = dto.id();
        this.descricao = dto.descricao();
        this.correta = dto.correta();
        
        if (dto.questaoId() != null) {
            Questao questao = new Questao();
            questao.setId(dto.questaoId());
            this.questao = questao;
        }
    }
}