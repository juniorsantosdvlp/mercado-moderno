package br.com.kanritech.market.controller;

import br.com.kanritech.market.model.Product;
import br.com.kanritech.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("product")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(productRepository.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Optional<Product> findProduct(@PathVariable("id") Long id) {


        return productRepository.findById(id);
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
}
