package com.jplatkiw.RESTdemo;

import java.time.LocalDate;
import java.util.UUID;

public class Driver {

    private final UUID id;
    private final String creationDate;

    private String firstname;
    private String lastname;
    private String date_of_birth;

    public Driver(String firstName, String lastName, String dateOfBirth) {
        this.id = UUID.randomUUID();
        this.creationDate = LocalDate.now().toString();
        this.firstname = firstName;
        this.lastname = lastName;
        this.date_of_birth = dateOfBirth;
    }

    public UUID getId() {
        return id;
    }

    public String getCreationDate() {
        return creationDate.toString();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDate_of_birth() {
        return date_of_birth.toString();
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
