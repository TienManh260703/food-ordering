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
@Table(name = "ingredient_categories")
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;
    @OneToMany(mappedBy = "ingredientCategory", cascade =  CascadeType.ALL)
    List<IngredientsItem> ingredients;
}
