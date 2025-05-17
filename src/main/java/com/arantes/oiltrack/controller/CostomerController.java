package com.arantes.oiltrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arantes.oiltrack.dto.costomer.CostomerRequestDTO;
import com.arantes.oiltrack.dto.costomer.CostomerResponseDTO;
import com.arantes.oiltrack.services.CostomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/costomers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CostomerController {

    @Autowired
    private CostomerService service;

    @GetMapping
    public ResponseEntity<List<CostomerResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostomerResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CostomerResponseDTO> post(@RequestBody CostomerRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostomerResponseDTO> put(@PathVariable Long id,
            @Valid @RequestBody CostomerRequestDTO CostomerDTO) {
        return ResponseEntity.ok(service.update(id, CostomerDTO));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
