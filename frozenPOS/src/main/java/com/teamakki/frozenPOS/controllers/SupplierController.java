package com.teamakki.frozenPOS.controllers;

import com.teamakki.frozenPOS.entities.Supplier;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "*")
public class SupplierController {
    private final SupplierService svc;
    public SupplierController(SupplierService svc) { this.svc = svc; }

    @GetMapping public List<Supplier> all() { return svc.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> get(@PathVariable Long id) {
        Supplier s = svc.getById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        return ResponseEntity.ok(s);
    }

    @PostMapping public Supplier create(@RequestBody Supplier s) { return svc.create(s); }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> update(@PathVariable Long id, @RequestBody Supplier updated) { return ResponseEntity.ok(svc.update(id, updated)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { svc.delete(id); return ResponseEntity.ok().build(); }
}
