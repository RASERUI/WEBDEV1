package com.teamakki.frozenPOS.controllers;

import com.teamakki.frozenPOS.entities.Product;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService svc;
    public ProductController(ProductService svc) { this.svc = svc; }

    @GetMapping public List<Product> all() { return svc.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        Product p = svc.getById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return ResponseEntity.ok(p);
    }

    @PostMapping public Product create(@RequestBody Product p) { return svc.create(p); }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product updated) {
        return ResponseEntity.ok(svc.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { svc.delete(id); return ResponseEntity.ok().build(); }
}
