package com.example.travelbuddyapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String city;
    private String country;
    private String province;
    private String zip;
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