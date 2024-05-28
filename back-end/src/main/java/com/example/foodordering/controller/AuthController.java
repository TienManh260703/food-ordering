package com.example.foodordering.controller;

import com.example.foodordering.config.JwtProvider;
import com.example.foodordering.dto.request.LoginRequest;
import com.example.foodordering.dto.response.AuthResponse;
import com.example.foodordering.enums.ROLE;
import com.example.foodordering.model.Cart;
import com.example.foodordering.model.User;
import com.example.foodordering.repository.CartRepository;
import com.example.foodordering.repository.UserRepository;
import com.example.foodordering.service.CustomUserDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtProvider jwtProvider;
    CustomUserDetailService customUserDetailService;
    CartRepository cartRepository;

    @PostMapping("signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if (isEmailExist != null) {
            throw new Exception("Email is already used with another account");
        }

        User createUser = User.builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();

        User saveUser = userRepository.save(createUser);

        Cart cart = Cart.builder()
                .customer(saveUser)
                .build();
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = jwtProvider.generateToken(authentication);


        AuthResponse authResponse = AuthResponse.builder()
                .jwt(jwt)
                .massage("Register success")
                .role(saveUser.getRole())
                .build();

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest request) {
        String username = request.getEmail();
        String password = request.getPassword();

        Authentication authentication = authenticate(username, password);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = AuthResponse.builder()
                .jwt(jwt)
                .massage("Register success")
                .role(ROLE.valueOf(role))
                .build();

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username ...");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid  password ...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
