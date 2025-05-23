package com.arantes.oiltrack.controllers;

import com.arantes.oiltrack.dto.visit.VisitRequestDTO;
import com.arantes.oiltrack.dto.visit.VisitResponseDTO;
import com.arantes.oiltrack.services.VisitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VisitController {

    @Autowired
    private VisitService service;

    @GetMapping
    public ResponseEntity<List<VisitResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<VisitResponseDTO> post(@RequestBody VisitRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitResponseDTO> put(@PathVariable Long id,
                                                   @Valid @RequestBody VisitRequestDTO visitDTO) {
        return ResponseEntity.ok(service.update(id, visitDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
