package com.vanvan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vanvan.exception.CnhAlreadyExistsException;
import com.vanvan.exception.CpfAlreadyExistsException;
import com.vanvan.exception.EmailAlreadyExistsException;
import com.vanvan.model.Administrator;
import com.vanvan.model.Driver;
import com.vanvan.model.Passenger;
import com.vanvan.repository.AdministratorRepository;
import com.vanvan.repository.DriverRepository;
import com.vanvan.repository.PassengerRepository;
import com.vanvan.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Driver registerDriver(Driver driver) {
        //verifica se o email já está cadastrado
        if (userRepository.findByEmail(driver.getEmail()) != null) {
            throw new EmailAlreadyExistsException(driver.getEmail());
        }
        //verifica se o cpf já está cadastrado (Busca global no UserRepository)
        if (userRepository.findByCpf(driver.getCpf()) != null) {
            throw new CpfAlreadyExistsException(driver.getCpf());
        }
        //verifica se a cnh já está cadastrada
        if (driverRepository.existsByCnh(driver.getCnh())) {
             throw new CnhAlreadyExistsException(driver.getCnh());
        }
        //salva a senha criptografada
        String encryptedPassword = passwordEncoder.encode(driver.getPassword());
        driver.setPassword(encryptedPassword);
        return driverRepository.save(driver);
    }

    public Passenger registerPassenger(Passenger passenger) {
        //verifica se o email já está cadastrado
        if (userRepository.findByEmail(passenger.getEmail()) != null) {
            throw new EmailAlreadyExistsException(passenger.getEmail());
        }
        //verifica se o cpf já está cadastrado (Busca global)
        if (userRepository.findByCpf(passenger.getCpf()) != null) {
            throw new CpfAlreadyExistsException(passenger.getCpf());
        }
        //salva a senha criptografada
        String encryptedPassword = passwordEncoder.encode(passenger.getPassword());
        passenger.setPassword(encryptedPassword);
        return passengerRepository.save(passenger);
    }

    public Administrator registerAdmin(Administrator administrator) {
        //verifica se o email já está cadastrado
        if (userRepository.findByEmail(administrator.getEmail()) != null) {
            throw new EmailAlreadyExistsException(administrator.getEmail());
        }
        //verifica se o cpf já está cadastrado (Busca global)
        if (userRepository.findByCpf(administrator.getCpf()) != null) {
            throw new CpfAlreadyExistsException(administrator.getCpf());
        }
        //salva a senha criptografada
        String encryptedPassword = passwordEncoder.encode(administrator.getPassword());
        administrator.setPassword(encryptedPassword);
        return administratorRepository.save(administrator);
    }
}