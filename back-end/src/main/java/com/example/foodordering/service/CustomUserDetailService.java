package com.example.foodordering.service;

import com.example.foodordering.enums.ROLE;
import com.example.foodordering.model.User;
import com.example.foodordering.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User existingUser = userRepository.findByEmail(username);
        if (existingUser != null) {
            throw new UsernameNotFoundException("User not found with email : " + username);
        }
        ROLE role = existingUser.getRole();
        if (role == null) {
            role = ROLE.ROLE_CUSTOMER;
        }
        List<GrantedAuthority> authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), authorities);
    }
}
