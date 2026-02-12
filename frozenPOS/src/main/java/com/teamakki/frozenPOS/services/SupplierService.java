package com.teamakki.frozenPOS.services;

import com.teamakki.frozenPOS.entities.Supplier;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository repo;
    public SupplierService(SupplierRepository repo) { this.repo = repo; }
    public List<Supplier> getAll() { return repo.findAll(); }
    public Optional<Supplier> getById(Long id) { return repo.findById(id); }
    public Supplier create(Supplier s) { return repo.save(s); }
    public Supplier update(Long id, Supplier updated) {
        Supplier s = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
        s.setSupplierName(updated.getSupplierName());
        s.setContactPerson(updated.getContactPerson());
        s.setSupplierEmail(updated.getSupplierEmail());
        return repo.save(s);
    }
    public void delete(Long id) { if (!repo.existsById(id)) throw new ResourceNotFoundException("Supplier not found with id " + id); repo.deleteById(id); }
}
