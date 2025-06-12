package com.arantes.oiltrack.controllers;

import com.arantes.oiltrack.dto.product.ProductRequestDTO;
import com.arantes.oiltrack.dto.product.ProductResponseDTO;
import com.arantes.oiltrack.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> post(@RequestBody ProductRequestDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> put(@PathVariable long id,
                                                  @Valid @RequestBody ProductRequestDTO productDTO) {
        return ResponseEntity.ok(service.update(id, productDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
