package com.example.foodordering.repository;

import com.example.foodordering.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , String> {
    public  User findByEmail(String username);
}
