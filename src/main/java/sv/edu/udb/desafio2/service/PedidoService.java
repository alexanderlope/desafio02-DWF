package sv.edu.udb.desafio2.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.desafio2.model.Cliente;
import sv.edu.udb.desafio2.model.Pedido;
import sv.edu.udb.desafio2.model.PedidoProducto;
import sv.edu.udb.desafio2.model.Producto;
import sv.edu.udb.desafio2.repository.ClienteRepository;
import sv.edu.udb.desafio2.repository.PedidoProductoRepository;
import sv.edu.udb.desafio2.repository.PedidoRepository;
import sv.edu.udb.desafio2.repository.ProductoRepository;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoProductoRepository pedidoProductoRepository;

    @Transactional
    public Pedido registrarPedido(Long clienteId, List<PedidoItemDTO> items, Pedido.MetodoPago metodoPago, Pedido.EstadoPedido estado) throws Exception {
        // Validar cliente
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new Exception("Cliente no encontrado"));

        if (items == null || items.isEmpty()) {
            throw new Exception("El pedido debe contener al menos un producto");
        }

        BigDecimal total = BigDecimal.ZERO;

        // Crear el pedido base
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setMetodoPago(metodoPago);
        pedido.setEstado(estado);
        pedido.setFechaPedido(java.time.LocalDateTime.now());

        List<PedidoProducto> detalles = new ArrayList<>();

        // Procesar cada item del pedido
        for (PedidoItemDTO item : items) {
            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new Exception("Producto no encontrado con ID: " + item.getProductoId()));

            // Validar stock
            if (producto.getStock() < item.getCantidad()) {
                throw new Exception("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Calcular precios
            BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad()));

            // Actualizar stock
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);

            // Crear detalle
            PedidoProducto detalle = new PedidoProducto();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(subtotal);

            detalles.add(detalle);
            total = total.add(subtotal);
        }

        pedido.setTotal(total);
        pedido.setProductos(detalles);

        // Guardar el pedido
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // Guardar los detalles del pedido
        for (PedidoProducto detalle : detalles) {
            detalle.setPedido(pedidoGuardado); // Set pedido con ID generado
            pedidoProductoRepository.save(detalle); // Uso correcto del repositorio
        }


        return pedidoGuardado;
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }
}
