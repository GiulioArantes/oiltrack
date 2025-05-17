package com.arantes.oiltrack.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arantes.oiltrack.dto.costomer.CostomerRequestDTO;
import com.arantes.oiltrack.dto.costomer.CostomerResponseDTO;
import com.arantes.oiltrack.models.Costomer;
import com.arantes.oiltrack.repositories.CostomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CostomerService {

    @Autowired
    private CostomerRepository repository;

    public List<CostomerResponseDTO> findAll() {
        return repository.findAll().stream().map(CostomerResponseDTO::new).toList();

    }

    public Costomer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consumidor não encontrado"));
    }

    public CostomerResponseDTO insert(CostomerRequestDTO data) {
        Costomer savedCostomer = repository.save(new Costomer(data));
        return new CostomerResponseDTO(savedCostomer);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public Costomer update(Long id, Costomer costomer) {
        Costomer obj = repository.getReferenceById(id);
        updateData(obj, costomer);
        return repository.save(obj);
    }

    private void updateData(Costomer entity, Costomer obj) {
        entity.setName(obj.getName());
        entity.setCnpj(obj.getCnpj());
        entity.setCorporateReason(obj.getCorporateReason());
        entity.setMail(obj.getMail());
        entity.setPhone(obj.getPhone());
        entity.setAddress(obj.getAddress());
    }
}
