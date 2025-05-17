package com.arantes.oiltrack.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arantes.oiltrack.dto.costomer.CostomerRequestDTO;
import com.arantes.oiltrack.dto.costomer.CostomerResponseDTO;
import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.Costomer;
import com.arantes.oiltrack.repositories.CostomerRepository;

@Service
public class CostomerService {

    @Autowired
    private CostomerRepository repository;

    public List<CostomerResponseDTO> findAll() {
        return repository.findAll().stream().map(CostomerResponseDTO::new).toList();

    }

    public CostomerResponseDTO findById(Long id) {
        try {
            return repository.findById(id).map(CostomerResponseDTO::new)
                    .orElseThrow(() -> new ResourceNotFoundException("Consumidor não encontrado pelo id: " + id));

        } catch (ResourceNotFoundException e) {
            throw e;
        }
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
        } // TODO Exception, Handler
    }

    public CostomerResponseDTO update(Long id, CostomerRequestDTO costomerDTO) {
        Costomer obj = repository.getReferenceById(id);
        updateData(obj, costomerDTO);
        return new CostomerResponseDTO(repository.save(obj)); // TODO Exception, Handler
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
