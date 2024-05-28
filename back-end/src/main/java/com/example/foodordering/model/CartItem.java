package com.example.foodordering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;
    @ManyToOne
    Food food;

    Integer quantity;

    List<String> ingredients;

    Float totalPrice;
}
