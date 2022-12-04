package com.example.travelbuddyapp.repositories;

import com.example.travelbuddyapp.entities.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepository extends JpaRepository<Confirmation,String> {
}
