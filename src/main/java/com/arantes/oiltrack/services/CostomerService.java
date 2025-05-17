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

    public CostomerResponseDTO findById(Long id) {
        return repository.findById(id).map(CostomerResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Consumidor não encontrado")); // Criar uma exception
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
        } // Criar uma excpetion
    }

    public CostomerResponseDTO update(Long id, CostomerRequestDTO costomerDTO) {
        Costomer obj = repository.getReferenceById(id);
        updateData(obj, costomerDTO);
        return new CostomerResponseDTO(repository.save(obj)); // Criar uma excpetion
    }

    private void updateData(Costomer entity, CostomerRequestDTO receivedCostomerDTO) {
        entity.setName(receivedCostomerDTO.name());
        entity.setCnpj(receivedCostomerDTO.cnpj());
        entity.setCorporateReason(receivedCostomerDTO.corporateReason());
        entity.setMail(receivedCostomerDTO.mail());
        entity.setPhone(receivedCostomerDTO.phone());
        entity.setAddress(receivedCostomerDTO.address());
    }
}
