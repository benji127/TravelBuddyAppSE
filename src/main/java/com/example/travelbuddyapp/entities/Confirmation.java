package com.example.travelbuddyapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Confirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer userId;
    private Integer tripId;
    private String name;
    private byte[] content;
    private int confirmationType;
    private Integer createdBy;
    private Date createdOn;
    private Integer lastUpdatedBy;
    private Date lastUpdatedOn;
}
