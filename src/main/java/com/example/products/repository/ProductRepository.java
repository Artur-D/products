package com.example.products.repository;

import com.example.products.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Repository<Product, Long> {

    //    Create a new product
    Product save(Product product);

    Optional<Product> findById(Long id);

    //    Retrieve a list of all products
    List<Product> findAll(Pageable pageable);


    //    Delete a product (​soft deletion​)
    void delete(Product product);

}
