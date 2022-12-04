package com.example.travelbuddyapp.entities;

public enum UserType {
    OWNER(1, "Customer"),
    ADMIN(2, "Admin");

    private int id;
    private String name;

    UserType(int id, String name) {
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
