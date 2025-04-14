package sv.edu.udb.desafio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.desafio2.model.PedidoProducto;
import sv.edu.udb.desafio2.model.PedidoProductoId;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, PedidoProductoId> {

}

