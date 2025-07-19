package com.arantes.oiltrack.controllers;

import com.arantes.oiltrack.dto.contact.ContactRequestDTO;
import com.arantes.oiltrack.dto.contact.ContactResponseDTO;
import com.arantes.oiltrack.services.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> post(@RequestBody ContactRequestDTO contactDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(contactDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> put(@PathVariable long id,
                                                  @Valid @RequestBody ContactRequestDTO contactDTO) {
        return ResponseEntity.ok(service.update(id, contactDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
