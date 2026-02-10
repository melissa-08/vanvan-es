package com.vanvan.model;

import com.vanvan.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "passengers")
public class Passenger extends User {

    public Passenger() {
        super();
    }

    public Passenger(String name, String cpf, String phone, String email, String password) {
        super(name, cpf, phone, email, password, UserRole.PASSENGER);
    }
}