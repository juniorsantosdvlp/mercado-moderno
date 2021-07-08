package br.com.kanritech.market.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int code;
    private String name;
    private String department;
    private BigDecimal sellValue;
    private String description;

}
