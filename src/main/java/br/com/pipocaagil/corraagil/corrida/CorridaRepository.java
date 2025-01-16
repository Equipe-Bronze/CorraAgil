package br.com.pipocaagil.corraagil.corrida;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorridaRepository extends JpaRepository<CorridaModel, Long> {
}
