package com.example.travelbuddyapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String trip_name;
    private Integer userId;
    private String location;
    private String city;
    private String country;
    private String model;
    private String starting_date;
    private String returning_date;
    private int longestTripDur;
    private int shortestTripDur;
    private String hobbies;
    private String languge;
    private int status;
    private Integer createdBy;
    private Date createdOn;
    private Integer lastUpdatedBy;
    private Date lastUpdatedOn;
    @Transient
    private MultipartFile[] pics;
    @Transient
    private MultipartFile[] docs;
}
