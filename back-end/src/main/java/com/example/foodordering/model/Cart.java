package com.example.foodordering.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    User customer;

    Float total;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL, orphanRemoval = true)
    List<CartItem> items = new ArrayList();
}
