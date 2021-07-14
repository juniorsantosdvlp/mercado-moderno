package br.com.kanritech.market.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "product",schema = "market")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull private Long code;

    @NotBlank private String name;
    @NotBlank private String department;
    @NotNull private BigDecimal sellValue;
    private String description;

}
