package com.vanvan.controller;

import org.springframework.web.bind.annotation.*;
import com.vanvan.dto.DriverAdminResponseDTO;
import com.vanvan.dto.DriverStatusUpdateDTO;
import com.vanvan.dto.DriverUpdateDTO;
import com.vanvan.dto.VehicleResponseDTO;
import com.vanvan.model.User;
import com.vanvan.enums.RegistrationStatus;
import com.vanvan.service.AdminService;
import com.vanvan.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final VehicleService vehicleService;

    @SuppressWarnings("DefaultAnnotationParam")
    @GetMapping("/drivers")
    public ResponseEntity<Page<DriverAdminResponseDTO>> listDrivers(
            @RequestParam(required = false) RegistrationStatus status,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(adminService.listDrivers(status, pageable));
    }

    @PutMapping("/drivers/{id}/status")
    public ResponseEntity<DriverAdminResponseDTO> updateDriverStatus(
            @PathVariable UUID id,
            @Valid @RequestBody DriverStatusUpdateDTO dto) {
        return ResponseEntity.ok(adminService.updateDriverStatus(id, dto));
    }

    @PutMapping("/drivers/{id}")
    public ResponseEntity<DriverAdminResponseDTO> updateDriver(
            @PathVariable UUID id,
            @Valid @RequestBody DriverUpdateDTO dto) {
        return ResponseEntity.ok(adminService.updateDriver(id, dto));
    }

    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable UUID id) {
        adminService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

   @GetMapping("/clients")
    public ResponseEntity<Page<User>> listClients(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(adminService.listClients(pageable));
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> createClient(@RequestBody User dto) {
        try {
            return ResponseEntity.ok(adminService.createClient(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(
            @PathVariable UUID id,
            @RequestBody User dto) {
        try {
            return ResponseEntity.ok(adminService.updateClient(id, dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable UUID id) {
        try {
            adminService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoints para gerenciar veículos
    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleResponseDTO>> listAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/vehicles/driver/{driverId}")
    public ResponseEntity<List<VehicleResponseDTO>> listVehiclesByDriver(@PathVariable UUID driverId) {
        return ResponseEntity.ok(vehicleService.getVehiclesByDriver(driverId));
    }

    @GetMapping("/vehicles/{vehicleId}")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable UUID vehicleId) {
        return ResponseEntity.ok(vehicleService.getVehicleById(vehicleId));
    }
}