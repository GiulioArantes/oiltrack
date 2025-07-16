package com.arantes.oiltrack.services;

import com.arantes.oiltrack.dto.afterSales.AfterSalesRequestDTO;
import com.arantes.oiltrack.dto.afterSales.AfterSalesResponseDTO;
import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.AfterSales;
import com.arantes.oiltrack.repositories.AfterSalesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AfterSalesService {

    @Autowired
    private AfterSalesRepository repository;

    public List<AfterSalesResponseDTO> findAll() {
        return repository.findAll().stream().map(AfterSalesResponseDTO::new).toList();
    }

    public AfterSalesResponseDTO findById(Long id) {
        return repository.findById(id).map(AfterSalesResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("After Sale not found by id: " + id));
    }

    public AfterSalesResponseDTO insert(AfterSalesRequestDTO data) {
        AfterSales afterSales = repository.save(new AfterSales(data));
        return new AfterSalesResponseDTO(afterSales);
    }

    public AfterSalesResponseDTO update(Long id, AfterSalesRequestDTO afterSalesDTO) {
        try {
            AfterSales afterSales = repository.getReferenceById(id);
            UpdateData(afterSales, afterSalesDTO);
            return new AfterSalesResponseDTO(repository.save(afterSales));
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("After Sale not found by id: " + id);
        }
    }

    private void UpdateData(AfterSales afterSales, AfterSalesRequestDTO afterSalesDTO) {
        afterSales.setDate(afterSalesDTO.date());
        afterSales.setDescription(afterSalesDTO.description());
        afterSales.setType(afterSalesDTO.type());
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("After Sale not found by id: " + id);
        repository.deleteById(id);
    }
}
