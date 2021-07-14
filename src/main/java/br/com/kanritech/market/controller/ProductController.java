package br.com.kanritech.market.controller;

import br.com.kanritech.market.exception.ProductNotFoundException;
import br.com.kanritech.market.model.Product;
import br.com.kanritech.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
}
