package com.arantes.oiltrack.services;

import com.arantes.oiltrack.dto.afterSales.AfterSalesRequestDTO;
import com.arantes.oiltrack.dto.afterSales.AfterSalesResponseDTO;
import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.AfterSales;
import com.arantes.oiltrack.models.Customer;
import com.arantes.oiltrack.repositories.AfterSalesRepository;
import com.arantes.oiltrack.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AfterSalesService {

    @Autowired
    private AfterSalesRepository afterSalesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<AfterSalesResponseDTO> findAll() {
        return afterSalesRepository.findAll().stream().map(AfterSalesResponseDTO::new).toList();
    }

    public AfterSalesResponseDTO findById(Long id) {
        return afterSalesRepository.findById(id).map(AfterSalesResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("After Sale not found by id: " + id));
    }

    public AfterSalesResponseDTO insert(AfterSalesRequestDTO data) {
        Customer customer = customerRepository.findById(data.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found by id: " + data.customerId()));

        AfterSales afterSales = afterSalesRepository.save(new AfterSales(data, customer));
        return new AfterSalesResponseDTO(afterSales);
    }

    public AfterSalesResponseDTO update(Long id, AfterSalesRequestDTO afterSalesDTO) {
        try {
            AfterSales afterSales = afterSalesRepository.getReferenceById(id);
            UpdateData(afterSales, afterSalesDTO);
            return new AfterSalesResponseDTO(afterSalesRepository.save(afterSales));
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("After Sale not found by id: " + id);
        }
    }

    private void UpdateData(AfterSales afterSales, AfterSalesRequestDTO afterSalesDTO) {
        afterSales.setDate(afterSalesDTO.date());
        afterSales.setDescription(afterSalesDTO.description());
        afterSales.setType(afterSalesDTO.type());

        Customer customer = customerRepository.findById(afterSalesDTO.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found by id: " + afterSalesDTO.customerId()));
        afterSales.setCustomer(customer);
    }

    public void delete(Long id) {
        if (!afterSalesRepository.existsById(id))
            throw new ResourceNotFoundException("After Sale not found by id: " + id);
        afterSalesRepository.deleteById(id);
    }
}
