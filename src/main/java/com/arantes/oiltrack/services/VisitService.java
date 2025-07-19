package com.arantes.oiltrack.services;

import com.arantes.oiltrack.dto.visit.VisitRequestDTO;
import com.arantes.oiltrack.dto.visit.VisitResponseDTO;
import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.Customer;
import com.arantes.oiltrack.models.Visit;
import com.arantes.oiltrack.repositories.CustomerRepository;
import com.arantes.oiltrack.repositories.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {

    @Autowired
    private VisitRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<VisitResponseDTO> findAll() {
        return repository.findAll().stream().map(VisitResponseDTO::new).toList();

    }

    public VisitResponseDTO findById(Long id) {
        return repository.findById(id).map(VisitResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Visit not found by id: " + id));
    }

    public VisitResponseDTO insert(VisitRequestDTO data) {
        Customer customer = customerRepository.findById(data.customerId())
                .orElseThrow(() -> new ResourceNotFoundException(("Customer not found by id: ") + data.customerId()));

        Visit savedVisit = repository.save(new Visit(data, customer));
        return new VisitResponseDTO(savedVisit);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Visit not found by id: " + id);
        }
        repository.deleteById(id);
    }

    public VisitResponseDTO update(Long id, VisitRequestDTO visitDTO) {
        try {
            Visit obj = repository.getReferenceById(id);
            updateData(obj, visitDTO);
            return new VisitResponseDTO(repository.save(obj));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Visit not found by id: " + id);
        }
    }

    private void updateData(Visit entity, VisitRequestDTO receivedVisitDTO) {
        entity.setVisitDate(receivedVisitDTO.visitDate());
        entity.setDescription(receivedVisitDTO.description());
        Customer customer = customerRepository.findById(receivedVisitDTO.customerId())
                .orElseThrow(() -> new ResourceNotFoundException(("Customer not found by id: ") + receivedVisitDTO.customerId()));
        entity.setCustomer(customer);
    }
}
