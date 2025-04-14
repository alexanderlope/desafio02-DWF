package sv.edu.udb.desafio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.desafio2.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}