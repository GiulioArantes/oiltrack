package com.arantes.oiltrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arantes.oiltrack.dto.costomer.CostomerRequestDTO;
import com.arantes.oiltrack.dto.costomer.CostomerResponseDTO;
import com.arantes.oiltrack.services.CostomerService;

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

    @PostMapping
    public ResponseEntity<CostomerResponseDTO> post(@RequestBody CostomerRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(data));
    }
}
