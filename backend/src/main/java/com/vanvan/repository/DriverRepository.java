package com.vanvan.repository;

import com.vanvan.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
    boolean existsByCnh(String cnh);
}