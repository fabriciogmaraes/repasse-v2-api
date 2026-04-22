package br.ufrn.imd.repasse_v2.service;

import br.ufrn.imd.repasse_v2.model.Veiculo;
import br.ufrn.imd.repasse_v2.model.AuditoriaLog;
import br.ufrn.imd.repasse_v2.repository.VeiculoRepository;
import br.ufrn.imd.repasse_v2.repository.AuditoriaLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private AuditoriaLogRepository auditoriaLogRepository;

    @Transactional // Garante que se um banco der erro, o outro não salva nada errado
    public Veiculo salvarVeiculo(Veiculo veiculo) {
        // 1. Salva o Veículo na Base A (Dados principais)
        Veiculo salvo = veiculoRepository.save(veiculo);

        // 2. Cria o Log para a Base B (Auditoria)
        AuditoriaLog log = new AuditoriaLog();
        log.setOperacao("CADASTRO_VEICULO");
        log.setRecursoId(salvo.getId()); // Pega o ID do carro que acabou de ser criado
        log.setDataHora(LocalDateTime.now()); // Pega data e hora atual

        // 3. Salva na Base B 
        auditoriaLogRepository.save(log);

        return salvo;
    }

    // Método para buscar tudo (CRUD)
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    // Método que usa o JOIN FETCH que criamos para carregar acessórios
    public Veiculo buscarComAcessorios(Long id) {
        return veiculoRepository.buscarComAcessorios(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }
    
    public List<Veiculo> buscarOportunidades() {
    return veiculoRepository.buscarOportunidades();
}
}