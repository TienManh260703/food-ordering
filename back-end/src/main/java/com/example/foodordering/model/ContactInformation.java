package com.example.foodordering.model;

import jakarta.persistence.Embedded;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactInformation {
    String email;
    String mobile;
    String twitter;
}
