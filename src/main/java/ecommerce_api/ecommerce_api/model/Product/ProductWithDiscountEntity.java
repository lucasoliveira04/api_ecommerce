package ecommerce_api.ecommerce_api.model.Product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "product_discount")
@Getter
@Setter
public class ProductWithDiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Produto produto;

    @Column(name = "initDiscount")
    private LocalDateTime initDiscount;

    @Column(name = "endDiscount")
    private LocalDateTime endDiscount;
}
