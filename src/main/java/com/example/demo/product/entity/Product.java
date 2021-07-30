package com.example.demo.product.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/** A Product * */
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "product_name")
  @NotNull
  private String productName;

  @Column(name = "price")
  @NotNull
  private BigDecimal price;

  @Column(name = "description")
  private String description;

  @Column(name = "category")
  @NotNull
  private String category;

  @Column(name = "availability")
  @NotNull
  private int availability;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Product product = (Product) o;

    return Objects.equals(id, product.id);
  }

  @Override
  public int hashCode() {
    return 2042274511;
  }

  @Override
  public String toString() {
    return "Product{"
        + "id="
        + id
        + ", productName='"
        + productName
        + '\''
        + ", price="
        + price
        + ", description='"
        + description
        + '\''
        + ", category='"
        + category
        + '\''
        + ", availability="
        + availability
        + '}';
  }
}
