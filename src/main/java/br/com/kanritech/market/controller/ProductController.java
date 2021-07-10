package br.com.kanritech.market.controller;

import br.com.kanritech.market.model.Product;
import br.com.kanritech.market.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(productRepository.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable("id") Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> fullUpdateProduct(@PathVariable("id") Long id, @RequestBody Product requestProduct) {
        Product foundProduct = findProduct(id);
        foundProduct = requestProduct;
        foundProduct.setId(id);
        return ResponseEntity.ok(productRepository.save(foundProduct));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchUpdateProduct(@PathVariable("id") Long id, @RequestBody Product requestProduct) {
        Product foundProduct = findProduct(id);
        foundProduct.setCode(Optional.ofNullable(requestProduct.getCode()).orElse(foundProduct.getCode()));
        foundProduct.setDepartment(Optional.ofNullable(requestProduct.getDepartment()).orElse(foundProduct.getDepartment()));
        foundProduct.setDescription(Optional.ofNullable(requestProduct.getDescription()).orElse(foundProduct.getDescription()));
        foundProduct.setName(Optional.ofNullable(requestProduct.getName()).orElse(foundProduct.getName()));
        foundProduct.setSellValue(Optional.ofNullable(requestProduct.getSellValue()).orElse(foundProduct.getSellValue()));
        return ResponseEntity.ok().body(productRepository.save(foundProduct));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product product = findProduct(id);
        productRepository.delete(product);
        return ResponseEntity.ok().body(product);

    }
}


