package com.example.products.controller;

import com.example.products.common.util.BigDecimalUtils;
import com.example.products.common.util.DtoUtils;
import com.example.products.common.util.ExceptionUtils;
import com.example.products.config.WebConfiguration;
import com.example.products.domain.Product;
import com.example.products.domain.ProductFactory;
import com.example.products.domain.jackson.ProductViews;
import com.example.products.dto.DtoValidatable;
import com.example.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.example.products.config.WebConfiguration.BASE_API_PATH;

@RestController
@RequestMapping(BASE_API_PATH + "/products")
public class ProductController {

    private final ProductService productService;
    private final WebConfiguration configuration;

    public ProductController(final ProductService productService, final WebConfiguration configuration) {
        this.productService = productService;
        this.configuration = configuration;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> findAllBy() {
        int max = configuration.provideQueryResultLimit();

        Pageable limit = PageRequest.of(0, max);
        List<Product> allProducts = productService.getAllProducts(limit);

        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "products")
    @Transactional
    public ResponseEntity<Product> addProduct(@RequestBody final AddProductRequest request) {
        request.validate();

        final Product product = ProductFactory.create(request.name.get(), request.price.get());
        productService.save(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "products")
    @Transactional
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") final Long id, @RequestBody final UpdateProductRequest request) {
        request.validate();

        final Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            final Product product = productOptional.get();
            product.updateProductInfo(request.name.get(), request.price.get());

            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private static interface ProductResponseView extends ProductViews.ValueObjects {
    }

    static final class AddProductRequest implements DtoValidatable {

        private static final Logger log = LoggerFactory.getLogger(AddProductRequest.class);

        public Optional<String> name;
        public Optional<BigDecimal> price;

        @Override
        public void validate() {
            if (!DtoUtils.isNotNullAndPresent(name)) {
                ExceptionUtils.throwIllegalArgument("ERR_20200416160228074", log);
            }
            if (!DtoUtils.isNotNullAndPresent(price)) {
                ExceptionUtils.throwIllegalArgument("ERR_20200416160338264", log);
            }

            if (BigDecimalUtils.isLessThanZero(price.get())) {
                ExceptionUtils.throwIllegalArgument("ERR_20200416160441830", log);
            }

        }
    }

    public static final class UpdateProductRequest implements DtoValidatable {

        private static final Logger log = LoggerFactory.getLogger(AddProductRequest.class);

        public Optional<String> name;
        public Optional<BigDecimal> price;

        @Override
        public void validate() {
            if (!DtoUtils.isNotNullAndPresent(name)) {
                ExceptionUtils.throwIllegalArgument("ERR_20200416213440596", log);
            }
            if (!DtoUtils.isNotNullAndPresent(price)) {
                ExceptionUtils.throwIllegalArgument("ERR_20200416213440597", log);
            }

            if (BigDecimalUtils.isLessThanZero(price.get())) {
                ExceptionUtils.throwIllegalArgument("ERR_20200416213440598", log);
            }

        }
    }
}
