package com.vanvan.model;

import com.vanvan.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver extends User {
    
    @Column(name = "pix_key")
    private String pixKey;

    @Column(unique = true)
    private String cnh;

    public Driver() {
        super();
    }

    public Driver(String name, String cpf, String phone, String email, String password, String pixKey, String cnh) {
        super(name, cpf, phone, email, password, UserRole.DRIVER);
        this.pixKey = pixKey;
        this.cnh = cnh;
    }

    public String getPixKey() {
        return pixKey;
    }
    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public String getCnh() {
        return cnh;
    }
    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
}