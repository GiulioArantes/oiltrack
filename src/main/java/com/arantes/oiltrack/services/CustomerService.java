package com.arantes.oiltrack.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arantes.oiltrack.dto.customer.CustomerRequestDTO;
import com.arantes.oiltrack.dto.customer.CustomerResponseDTO;
import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.Customer;
import com.arantes.oiltrack.repositories.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.util.ReflectionUtils;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<CustomerResponseDTO> findAll() {
        return repository.findAll().stream().map(CustomerResponseDTO::new).toList();

    }

    public CustomerResponseDTO findById(Long id) {
        return repository.findById(id).map(CustomerResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Consumer not found by id: " + id));
    }

    public CustomerResponseDTO insert(CustomerRequestDTO data) {
        Customer savedCustomer = repository.save(new Customer(data));
        return new CustomerResponseDTO(savedCustomer);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Consumer not found by id: " + id);
        }
        repository.deleteById(id);
    }

    public CustomerResponseDTO update(Long id, CustomerRequestDTO customerDTO) {
        try {
            Customer obj = repository.getReferenceById(id);
            updateData(obj, customerDTO);
            return new CustomerResponseDTO(repository.save(obj));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Consumer not found by id: " + id);
        }
    }

    @Transactional
    public CustomerResponseDTO updatePatch(Long id, Map<String, Object> fields) {
            Customer obj = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Consumer not found by id: " + id));

            merge(fields, obj);
            return new CustomerResponseDTO(obj);
    }

    private void merge(Map<String, Object> fields, Customer customer) {
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customerConvert = objectMapper.convertValue(fields, Customer.class);

        fields.forEach((propertyName, propertyValue) -> {
            Field field = ReflectionUtils.findField(Customer.class, propertyName);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, customerConvert);

            ReflectionUtils.setField(field, customer, newValue);
        });
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
