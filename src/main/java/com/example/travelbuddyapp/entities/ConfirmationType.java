package com.example.travelbuddyapp.entities;

public enum ConfirmationType {
    ID(1, "Identity Card"),
    PASSPORT(2, "Passport"),
    INSURANCE_NUMBER(3, "Insurance Number"),
    USER_PHOTO(4, "User Photo");

    private int id;
    private String name;

    ConfirmationType(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
