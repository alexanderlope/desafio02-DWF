package sv.edu.udb.desafio2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.desafio2.model.Producto;
import sv.edu.udb.desafio2.repository.ProductoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository repo;

    @PostMapping
    public ResponseEntity<Producto> registrar(@RequestBody @Valid Producto producto) {
        return ResponseEntity.ok(repo.save(producto));
    }

    @GetMapping
    public List<Producto> listar() {
        return repo.findAll();
    }
}

