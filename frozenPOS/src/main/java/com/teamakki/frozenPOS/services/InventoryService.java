package com.teamakki.frozenPOS.services;

import com.teamakki.frozenPOS.entities.Inventory;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository repo;
    public InventoryService(InventoryRepository repo) { this.repo = repo; }
    public List<Inventory> getAll() { return repo.findAll(); }
    public Optional<Inventory> getById(Long id) { return repo.findById(id); }
    public Inventory create(Inventory inv) { return repo.save(inv); }
    public Inventory update(Long id, Inventory updated) {
        Inventory inv = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id " + id));
        inv.setQuantity(updated.getQuantity());
        inv.setUnit(updated.getUnit());
        inv.setLastUpdated(updated.getLastUpdated());
        return repo.save(inv);
    }
    public void delete(Long id) { if (!repo.existsById(id)) throw new ResourceNotFoundException("Inventory not found with id " + id); repo.deleteById(id); }
}
