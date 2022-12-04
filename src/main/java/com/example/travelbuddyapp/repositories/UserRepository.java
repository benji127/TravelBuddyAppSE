package com.example.travelbuddyapp.repositories;

import com.example.travelbuddyapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    List<User> findUserByEmail(String email);
}
