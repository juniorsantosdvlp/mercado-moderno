package br.com.kanritech.market.repository;

import br.com.kanritech.market.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    public Page<Product> findByDepartmentId(Long id, Pageable pageable);
}
