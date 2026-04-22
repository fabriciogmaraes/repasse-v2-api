package br.ufrn.imd.repasse_v2.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class AuditoriaLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String operacao; // Ex: "CADASTRO" [cite: 40]
    private Long recursoId;  // ID do objeto criado [cite: 40]
    private LocalDateTime dataHora; // Data e hora [cite: 40]
}