package com.arantes.oiltrack.controllers;

import com.arantes.oiltrack.dto.sale.SaleRequestDTO;
import com.arantes.oiltrack.dto.sale.SaleResponseDTO;
import com.arantes.oiltrack.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> post(@RequestBody SaleRequestDTO saleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(saleDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> put(@PathVariable long id,
                                               @Valid @RequestBody SaleRequestDTO saleDTO) {
        return ResponseEntity.ok(service.update(id, saleDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
