package com.teamakki.frozenPOS.controllers;

import com.teamakki.frozenPOS.entities.SaleProduct;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.services.SaleProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale-products")
@CrossOrigin(origins = "*")
public class SaleProductController {
    private final SaleProductService svc;
    public SaleProductController(SaleProductService svc) { this.svc = svc; }

    @GetMapping public List<SaleProduct> all() { return svc.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<SaleProduct> get(@PathVariable Long id) {
        SaleProduct sp = svc.getById(id).orElseThrow(() -> new ResourceNotFoundException("SaleProduct not found with id " + id));
        return ResponseEntity.ok(sp);
    }

    @PostMapping public SaleProduct create(@RequestBody SaleProduct sp) { return svc.create(sp); }

    @PutMapping("/{id}")
    public ResponseEntity<SaleProduct> update(@PathVariable Long id, @RequestBody SaleProduct updated) { return ResponseEntity.ok(svc.update(id, updated)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { svc.delete(id); return ResponseEntity.ok().build(); }
}
