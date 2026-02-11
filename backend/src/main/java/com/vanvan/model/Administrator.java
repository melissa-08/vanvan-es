package com.vanvan.model;

import com.vanvan.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrators")
public class Administrator extends User {
    
    public Administrator() {
        super();
    }

    public Administrator(String name, String cpf, String phone, String email, String password) {
        super(name, cpf, phone, email, password, UserRole.ADMIN);
    }
}