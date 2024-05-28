package com.example.foodordering.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String description;
    Float price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category foodCategory;

    @Column(length = 1000)
    @ElementCollection
    List<String> images;

    Boolean available;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;
    Boolean isVegetarian;// chay

    Boolean isSeasonal;// theo mùa

    @ManyToMany
    List<IngredientsItem> ingredients = new ArrayList();
    @Column(name = "creation_date")
    Date creationDate;
}
