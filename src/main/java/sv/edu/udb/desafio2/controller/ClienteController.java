package sv.edu.udb.desafio2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.desafio2.model.Cliente;
import sv.edu.udb.desafio2.repository.ClienteRepository;

import java.util.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Registrar un nuevo cliente
    @PostMapping
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El nombre del cliente es obligatorio"));
        }

        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El email del cliente es obligatorio"));
        }

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "El email ya está registrado"));
        }

        cliente.setActivo(true);
        cliente.setFechaRegistro(java.time.LocalDateTime.now());

        Cliente nuevoCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    // Obtener lista de todos los clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}

