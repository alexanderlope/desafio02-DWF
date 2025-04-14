package sv.edu.udb.desafio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.desafio2.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);
}
