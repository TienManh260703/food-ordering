package com.example.foodordering.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ingredients_item")
public class IngredientsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;

    @ManyToOne
    @JoinColumn(name = "ingredient_category_id")
    IngredientCategory ingredientCategory;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    Boolean inStoke= true;

}
