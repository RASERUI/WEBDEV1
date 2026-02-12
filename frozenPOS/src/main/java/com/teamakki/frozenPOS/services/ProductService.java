package com.teamakki.frozenPOS.services;

import com.teamakki.frozenPOS.entities.Product;
import com.teamakki.frozenPOS.exception.ResourceNotFoundException;
import com.teamakki.frozenPOS.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> getAll() { return repo.findAll(); }
    public Optional<Product> getById(Long id) { return repo.findById(id); }
    public Product create(Product p) { return repo.save(p); }
    public Product update(Long id, Product updated) {
        Product p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        p.setProductName(updated.getProductName());
        p.setBasePrice(updated.getBasePrice());
        p.setListPrice(updated.getListPrice());
        p.setQuantity(updated.getQuantity());
        p.setOrderPoint(updated.getOrderPoint());
        return repo.save(p);
    }
    public void delete(Long id) { if (!repo.existsById(id)) throw new ResourceNotFoundException("Product not found with id " + id); repo.deleteById(id); }
}
