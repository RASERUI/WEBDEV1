package com.teamakki.frozenPOS.controllers;

import com.teamakki.frozenPOS.entities.Inventory;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.services.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@CrossOrigin(origins = "*")
public class InventoryController {
    private final InventoryService svc;
    public InventoryController(InventoryService svc) { this.svc = svc; }

    @GetMapping public List<Inventory> all() { return svc.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> get(@PathVariable Long id) {
        Inventory inv = svc.getById(id).orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id " + id));
        return ResponseEntity.ok(inv);
    }

    @PostMapping public Inventory create(@RequestBody Inventory inv) { return svc.create(inv); }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> update(@PathVariable Long id, @RequestBody Inventory updated) { return ResponseEntity.ok(svc.update(id, updated)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { svc.delete(id); return ResponseEntity.ok().build(); }
}
