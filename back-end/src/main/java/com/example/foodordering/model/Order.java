package com.example.foodordering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @ManyToOne
    User customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    @Column(name = "total_amount")
    Float totalAmount;

    @Column(name = "order_status")
    String orderStatus;

    @Column(name = "created_at")
    Date createdAt;

    @ManyToOne
    @JoinColumn(name = "delivery_address")
    Address deliveryAddress;

    @OneToMany
    List<OrderItem> items;

//    Payment payment;

    @Column(name = "total_item")
    Integer totalItem;

    @Column(name = "total_price")
    Float totalPrice;
}
