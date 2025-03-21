package ecommerce_api.ecommerce_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ecommerce_api.ecommerce_api.enums.CategoryProductEnum;
import ecommerce_api.ecommerce_api.services.Discount.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "product_main")
@Getter @Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String productName;

    @Column(name = "price")
    private Double productPrice;

    @JsonIgnore
    @Column(name = "priceWithDiscount")
    private Double productPriceWithDiscount;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "productQuantity")
    private int productQuantity;

    @Column(name = "category_product")
    @Enumerated(EnumType.STRING)
    private CategoryProductEnum categoryProduct;

    @JsonIgnore
    @Column(name = "hasDiscount")
    private boolean hasDiscount;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
