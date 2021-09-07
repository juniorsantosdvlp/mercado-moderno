package br.com.kanritech.market.dto;

import br.com.kanritech.market.model.Department;
import br.com.kanritech.market.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private Long code;
    private Long departmentId;
    private BigDecimal sellValue;
    private String description;

    public Product toProduct(Department department) {
        Product product = new Product();
        product.setCode(this.code);
        product.setDepartment(department);
        product.setDescription(this.description);
        product.setName(this.name);
        product.setSellValue(this.sellValue);
        return product;
    }
}
