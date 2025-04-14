package sv.edu.udb.desafio2.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PedidoProductoId implements Serializable {
    private Long pedidoId;
    private Long productoId;

}
