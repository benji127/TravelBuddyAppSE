package com.example.travelbuddyapp.services;

import com.example.travelbuddyapp.entities.User;
import com.example.travelbuddyapp.entities.Trip;
import com.example.travelbuddyapp.repositories.TripRepository;

import java.util.Date;
import java.util.List;

public class TripServiceImpl {
    public static Trip addTrip(Trip trip, TripRepository repository, Integer userId) {
        try {
            if (checkIfTripExists(trip.getLocation(), repository)) {
                throw new Exception("Trip already exists!");
            }
            trip.setStatus(1);
            trip.setUserId(userId);
            trip.setCreatedOn(new Date());
            trip.setLastUpdatedOn(new Date());

            Trip v = repository.save(trip);
            v.setCreatedBy(userId);
            v.setLastUpdatedBy(userId);
            repository.save(v);
            return v;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static boolean checkIfTripExists(String location, TripRepository repo) {
        List<Trip> trips = repo.findTripByLocation(location);
        if (trips.isEmpty()) {
            return false;
        }
        return true;
    }
}
