package com.teamakki.frozenPOS.services;

import com.teamakki.frozenPOS.entities.SaleProduct;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.repository.SaleProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleProductService {
    private final SaleProductRepository repo;
    public SaleProductService(SaleProductRepository repo) { this.repo = repo; }
    public List<SaleProduct> getAll() { return repo.findAll(); }
    public Optional<SaleProduct> getById(Long id) { return repo.findById(id); }
    public SaleProduct create(SaleProduct sp) { return repo.save(sp); }
    public SaleProduct update(Long id, SaleProduct updated) {
        SaleProduct sp = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("SaleProduct not found with id " + id));
        sp.setSale(updated.getSale());
        sp.setProduct(updated.getProduct());
        sp.setInventory(updated.getInventory());
        sp.setQuantity(updated.getQuantity());
        sp.setBasePrice(updated.getBasePrice());
        sp.setListPrice(updated.getListPrice());
        return repo.save(sp);
    }
    public void delete(Long id) { if (!repo.existsById(id)) throw new ResourceNotFoundException("SaleProduct not found with id " + id); repo.deleteById(id); }
}
