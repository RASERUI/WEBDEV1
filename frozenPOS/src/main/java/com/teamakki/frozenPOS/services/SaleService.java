package com.teamakki.frozenPOS.services;

import com.teamakki.frozenPOS.entities.Sale;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private final SaleRepository repo;
    public SaleService(SaleRepository repo) { this.repo = repo; }
    public List<Sale> getAll() { return repo.findAll(); }
    public Optional<Sale> getById(Long id) { return repo.findById(id); }
    public Sale create(Sale s) { return repo.save(s); }
    public Sale update(Long id, Sale updated) {
        Sale s = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sale not found with id " + id));
        s.setSaleDateTime(updated.getSaleDateTime());
        s.setTotalAmt(updated.getTotalAmt());
        s.setPaymentMethod(updated.getPaymentMethod());
        s.setSaleType(updated.getSaleType());
        s.setSaleProducts(updated.getSaleProducts());
        return repo.save(s);
    }
    public void delete(Long id) { if (!repo.existsById(id)) throw new ResourceNotFoundException("Sale not found with id " + id); repo.deleteById(id); }
}
