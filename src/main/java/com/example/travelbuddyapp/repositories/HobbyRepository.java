package com.example.travelbuddyapp.repositories;

import com.example.travelbuddyapp.entities.Hobbies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobbies,Long> {
//        List<Hobbies> findByModelContaining(String kw);
//        List<Hobbies> findTripByLocation(String location);
//        List<Hobbies> findByHobby(String hobbies);
}
