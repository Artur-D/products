package com.example.products.service;

import com.example.products.domain.Product;
import com.example.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(final Long id) {
        final Optional<Product> productOptional = productRepository.findById(id);
        return productOptional;
    }

    @Cacheable("products")
    public List<Product> getAllProducts(final Pageable pageable) {
        final List<Product> products = productRepository.findAll(pageable);
        return products;
    }

    public void save(final Product product) {
        productRepository.save(product);
    }

}
