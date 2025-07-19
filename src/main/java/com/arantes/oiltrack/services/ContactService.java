package com.arantes.oiltrack.services;

import com.arantes.oiltrack.dto.contact.ContactRequestDTO;
import com.arantes.oiltrack.dto.contact.ContactResponseDTO;
import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.Contact;
import com.arantes.oiltrack.models.Customer;
import com.arantes.oiltrack.repositories.ContactRepository;
import com.arantes.oiltrack.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<ContactResponseDTO> findAll() {
        return contactRepository.findAll().stream().map(ContactResponseDTO::new).toList();

    }

    public ContactResponseDTO findById(Long id) {
        return contactRepository.findById(id).map(ContactResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found by id: " + id));
    }

    public ContactResponseDTO insert(ContactRequestDTO data) {
        Customer customer = customerRepository.findById(data.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found by id: " + data.customerId()));

        Contact savedContact = contactRepository.save(new Contact(data, customer));
        return new ContactResponseDTO(savedContact);
    }

    public void delete(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contact not found by id: " + id);
        }
        contactRepository.deleteById(id);
    }

    public ContactResponseDTO update(Long id, ContactRequestDTO contactDTO) {
        try {
            Contact obj = contactRepository.getReferenceById(id);
            updateData(obj, contactDTO);
            return new ContactResponseDTO(contactRepository.save(obj));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Contact not found by id: " + id);
        }
    }

    private void updateData(Contact entity, ContactRequestDTO receivedContactDTO) {
        entity.setName(receivedContactDTO.name());
        entity.setSector(receivedContactDTO.sector());
        entity.setLandline(receivedContactDTO.landline());
        entity.setExtension(receivedContactDTO.extension());
        entity.setPhone(receivedContactDTO.phone());

        Customer customer = customerRepository.findById(receivedContactDTO.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found by id: " + receivedContactDTO.customerId()));

        entity.setCustomer(customer);
    }
}
