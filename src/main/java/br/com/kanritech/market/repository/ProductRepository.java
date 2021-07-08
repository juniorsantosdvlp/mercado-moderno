package br.com.kanritech.market.repository;

import br.com.kanritech.market.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
