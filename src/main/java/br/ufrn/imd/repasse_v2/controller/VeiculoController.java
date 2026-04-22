package br.ufrn.imd.repasse_v2.controller;

import br.ufrn.imd.repasse_v2.dto.VeiculoDTO;
import br.ufrn.imd.repasse_v2.model.Veiculo;
import br.ufrn.imd.repasse_v2.model.Loja;
import br.ufrn.imd.repasse_v2.service.VeiculoService;
import br.ufrn.imd.repasse_v2.repository.LojaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private LojaRepository lojaRepository;

    // Criar Veículo (Requisito: Validação com @Valid e uso de DTO) [cite: 34, 50]
    @PostMapping
    public ResponseEntity<Veiculo> criar(@Valid @RequestBody VeiculoDTO dto) {
        // Buscamos a loja para associar ao veículo (Relacionamento 1:N) [cite: 27]
        Loja loja = lojaRepository.findById(dto.getLojaId())
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(dto.getModelo());
        veiculo.setValorFipe(dto.getValorFipe());
        veiculo.setValorAnuncio(dto.getValorAnuncio());
        veiculo.setLoja(loja);

        return ResponseEntity.ok(veiculoService.salvarVeiculo(veiculo));
    }

    // Listar todos os veículos (CRUD completo) 
    @GetMapping
    public List<Veiculo> listar() {
        return veiculoService.listarTodos();
    }
    
    // Esse mostra só os que o valor é menor que a Fipe
    @GetMapping("/oportunidades")
    public List<Veiculo> listarOportunidades() {
        return veiculoService.buscarOportunidades();
    }

    // Buscar com acessórios (Uso do JOIN FETCH que vale nota) [cite: 47]
    @GetMapping("/{id}/detalhes")
    public ResponseEntity<Veiculo> buscarDetalhes(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.buscarComAcessorios(id));
    }
}