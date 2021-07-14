package br.com.kanritech.market.controller;

import br.com.kanritech.market.exception.ProductNotFoundException;
import br.com.kanritech.market.model.Product;
import br.com.kanritech.market.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<Product> findProduct(@PathVariable("id") Long id) {


        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).findProduct(id)).withSelfRel(),
                linkTo(methodOn(ProductController.class).findAll()).withRel("products"));
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
