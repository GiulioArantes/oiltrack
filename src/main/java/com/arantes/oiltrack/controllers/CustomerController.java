package com.arantes.oiltrack.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arantes.oiltrack.dto.customer.CustomerRequestDTO;
import com.arantes.oiltrack.dto.customer.CustomerResponseDTO;
import com.arantes.oiltrack.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> post(@RequestBody CustomerRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> put(@PathVariable Long id,
                                                   @Valid @RequestBody CustomerRequestDTO CustomerDTO) {
        return ResponseEntity.ok(service.update(id, CustomerDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> patch(@PathVariable Long id,
                                                     @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(service.updatePatch(id, fields));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
