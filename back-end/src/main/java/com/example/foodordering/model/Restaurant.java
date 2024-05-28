package com.example.foodordering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @OneToOne
    @JoinColumn(name = "owner_id")
    User owner;
    String name;

    String description;

    @Column(name = "cuisine_type")
    String cuisineType;

    @OneToOne
    Address address;

    @Embedded
    @Column(name = "contact_information")
    ContactInformation contactInformation;
    @Column(name = "opening_hours")
    String openingHours;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Order> orders = new ArrayList<>();
    @ElementCollection
    @Column(length = 1000)
    List<String> images = new ArrayList<>();
    @Column(name = "registration_date")
    LocalDateTime registrationDate;

    Boolean open;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL,orphanRemoval = true)
    List<Food> foods = new ArrayList<>();
}
