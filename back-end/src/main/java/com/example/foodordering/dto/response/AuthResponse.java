package com.example.foodordering.dto.response;

import com.example.foodordering.enums.ROLE;
import lombok.*;
import lombok.experimental.FieldDefaults;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {
    String jwt;
    String massage;
    ROLE role;
}
