package com.teamakki.frozenPOS.controllers;

import com.teamakki.frozenPOS.entities.Sale;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.services.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SaleController {
    private final SaleService svc;
    public SaleController(SaleService svc) { this.svc = svc; }

    @GetMapping public List<Sale> all() { return svc.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> get(@PathVariable Long id) {
        Sale s = svc.getById(id).orElseThrow(() -> new ResourceNotFoundException("Sale not found with id " + id));
        return ResponseEntity.ok(s);
    }

    @PostMapping public Sale create(@RequestBody Sale s) { return svc.create(s); }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> update(@PathVariable Long id, @RequestBody Sale updated) { return ResponseEntity.ok(svc.update(id, updated)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { svc.delete(id); return ResponseEntity.ok().build(); }
}
