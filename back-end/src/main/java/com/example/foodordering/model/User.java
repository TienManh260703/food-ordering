package com.example.foodordering.model;

import com.example.foodordering.dto.RestaurantDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(name = "full_name")
    String fullName;
    String email;
    String password;
    ROLE role;
    @JsonIgnore//loại bỏ order -> json get user
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")// xem lại
    List<Order> orders = new ArrayList<>();
    @ElementCollection
    List<RestaurantDto> favorites= new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Address> addresses= new ArrayList<>();
    String status;

}
