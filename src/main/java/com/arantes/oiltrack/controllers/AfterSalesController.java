package com.arantes.oiltrack.controllers;

import com.arantes.oiltrack.dto.afterSales.AfterSalesRequestDTO;
import com.arantes.oiltrack.dto.afterSales.AfterSalesResponseDTO;
import com.arantes.oiltrack.services.AfterSalesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aftersales")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AfterSalesController {

    @Autowired
    private AfterSalesService service;

    @GetMapping
    public ResponseEntity<List<AfterSalesResponseDTO>> getAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AfterSalesResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AfterSalesResponseDTO> post(@RequestBody AfterSalesRequestDTO afterSalesDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(afterSalesDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AfterSalesResponseDTO> put(@PathVariable long id,
                                                  @Valid @RequestBody AfterSalesRequestDTO afterSalesDTO) {
        return ResponseEntity.ok(service.update(id, afterSalesDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
