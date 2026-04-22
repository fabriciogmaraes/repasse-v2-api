package br.ufrn.imd.repasse_v2.repository;

import br.ufrn.imd.repasse_v2.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {
}