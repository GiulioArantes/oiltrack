package com.arantes.oiltrack.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arantes.oiltrack.dto.customer.CustomerRequestDTO;
import com.arantes.oiltrack.dto.customer.CustomerResponseDTO;
import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.Customer;
import com.arantes.oiltrack.repositories.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<CustomerResponseDTO> findAll() {
        return repository.findAll().stream().map(CustomerResponseDTO::new).toList();

    }

    public CustomerResponseDTO findById(Long id) {
        return repository.findById(id).map(CustomerResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Consumidor não encontrado pelo id: " + id));
    }

    public CustomerResponseDTO insert(CustomerRequestDTO data) {
        Customer savedCustomer = repository.save(new Customer(data));
        return new CustomerResponseDTO(savedCustomer);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } // TODO Exception, Handler
    }

    public CustomerResponseDTO update(Long id, CustomerRequestDTO costomerDTO) {
        try {
            Customer obj = repository.getReferenceById(id);
            updateData(obj, costomerDTO);
            return new CustomerResponseDTO(repository.save(obj));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Consumidor não encontrado pelo id: " + id);
        }
    }

    private void updateData(Customer entity, CustomerRequestDTO receivedCustomerDTO) {
        entity.setName(receivedCustomerDTO.name());
        entity.setCnpj(receivedCustomerDTO.cnpj());
        entity.setCorporateReason(receivedCustomerDTO.corporateReason());
        entity.setMail(receivedCustomerDTO.mail());
        entity.setPhone(receivedCustomerDTO.phone());
        entity.setAddress(receivedCustomerDTO.address());
    }
}
