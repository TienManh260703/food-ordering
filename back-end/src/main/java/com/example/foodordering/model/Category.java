package com.example.foodordering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;
}
