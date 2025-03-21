package ecommerce_api.ecommerce_api.model;

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

    @Column(name = "description")
    private String productDescription;

    @Column(name = "productQuantity")
    private int productQuantity;

    @Column(name = "category_product")
    private String categoryProduct;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = LocalDateTime.now().format(formatter);
        this.createdAt = LocalDateTime.now();
    }

}
