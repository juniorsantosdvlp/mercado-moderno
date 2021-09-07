package br.com.kanritech.market.service;

import br.com.kanritech.market.dto.ProductDTO;
import br.com.kanritech.market.model.Product;
import br.com.kanritech.market.repository.DepartmentRepository;
import br.com.kanritech.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {
    @Autowired protected ProductRepository productRepository;
    @Autowired protected DepartmentRepository departmentRepository;

    @Transactional
    public Product insert (ProductDTO dto){
       return productRepository.save(dto.toProduct(departmentRepository.findById(dto.getDepartmentId()).get()));
    }

    public Page<Product> getProductsByDepartment(Long id, Pageable pageable) {

        Page<Product> products = productRepository.findByDepartmentId(id,pageable);
         products.forEach(x -> System.out.println(x.getDescription()));
        return products ;
    }
}
