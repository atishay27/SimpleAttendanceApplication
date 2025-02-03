package dev.atishay.projects.attendance.models;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data // Generates getters, setters, toString, equals, and hashCode methods
public class User {
    private long userID; // Auto-increment field
    private String name;
    private int age;
    private String emailID;
}

