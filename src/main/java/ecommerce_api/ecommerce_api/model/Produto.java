package ecommerce_api.ecommerce_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "product")
@Getter @Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;
}
