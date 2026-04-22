package br.ufrn.imd.repasse_v2.repository;

import br.ufrn.imd.repasse_v2.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    // Requisito: Consulta usando JPQL [cite: 44]
    @Query("SELECT v FROM Veiculo v WHERE v.valorAnuncio < v.valorFipe")
    List<Veiculo> buscarOportunidades();

    // Requisito: Consulta usando SQL Nativo [cite: 45]
    @Query(value = "SELECT * FROM veiculo WHERE valor_anuncio > 100000", nativeQuery = true)
    List<Veiculo> buscarCarrosLuxo();

    // Requisito: Consulta usando JOIN FETCH (Eager loading) 
    @Query("SELECT v FROM Veiculo v JOIN FETCH v.acessorios WHERE v.id = :id")
    Optional<Veiculo> buscarComAcessorios(Long id);
}