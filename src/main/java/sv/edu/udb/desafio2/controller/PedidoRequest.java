package sv.edu.udb.desafio2.controller;

import sv.edu.udb.desafio2.model.Pedido;
import sv.edu.udb.desafio2.service.PedidoItemDTO;

import java.util.List;

public class PedidoRequest {
    private Long clienteId;
    private Pedido.MetodoPago metodoPago;
    private Pedido.EstadoPedido estado;
    private List<PedidoItemDTO> items;

    // Getters y Setters
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Pedido.MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(Pedido.MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Pedido.EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(Pedido.EstadoPedido estado) {
        this.estado = estado;
    }

    public List<PedidoItemDTO> getItems() {
        return items;
    }

    public void setItems(List<PedidoItemDTO> items) {
        this.items = items;
    }
}

