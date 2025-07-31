package com.ecomerse.ecomerse.service;

import com.ecomerse.ecomerse.model.Product;
import com.ecomerse.ecomerse.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepository;

    @Autowired
    public ProductService(ProductRepo productRepository) { this.productRepository = productRepository; }

    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public Optional<Product> getProductById(Long id) { return productRepository.findById(id); }
    public Product createProduct(Product product) { return productRepository.save(product); }
    public void deleteProduct(Long id) { productRepository.deleteById(id); }
}
