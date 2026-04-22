package br.ufrn.imd.repasse_v2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private Double valorFipe;
    private Double valorAnuncio;

    // Relacionamento Many-to-One: Muitos veículos para uma Loja [cite: 27]
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "loja_id")
    private Loja loja;

    // Relacionamento Many-to-Many: Carro tem vários acessórios e vice-versa [cite: 26]
    @ManyToMany
    @JoinTable(
        name = "veiculo_acessorio",
        joinColumns = @JoinColumn(name = "veiculo_id"),
        inverseJoinColumns = @JoinColumn(name = "acessorio_id")
    )
    private List<Acessorio> acessorios = new ArrayList<>();
}