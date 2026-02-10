package com.vanvan.repository;

import com.vanvan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    
    User findByEmail(String email);

    User findByCpf(String cpf);
}