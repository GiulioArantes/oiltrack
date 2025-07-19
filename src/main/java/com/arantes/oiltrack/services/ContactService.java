package com.arantes.oiltrack.services;

import com.arantes.oiltrack.dto.contact.ContactRequestDTO;
import com.arantes.oiltrack.dto.contact.ContactResponseDTO;
import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.Contact;
import com.arantes.oiltrack.repositories.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public List<ContactResponseDTO> findAll() {
        return repository.findAll().stream().map(ContactResponseDTO::new).toList();

    }

    public ContactResponseDTO findById(Long id) {
        return repository.findById(id).map(ContactResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found by id: " + id));
    }

    public ContactResponseDTO insert(ContactRequestDTO data) {
        Contact savedContact = repository.save(new Contact(data));
        return new ContactResponseDTO(savedContact);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Contact not found by id: " + id);
        }
        repository.deleteById(id);
    }

    public ContactResponseDTO update(Long id, ContactRequestDTO contactDTO) {
        try {
            Contact obj = repository.getReferenceById(id);
            updateData(obj, contactDTO);
            return new ContactResponseDTO(repository.save(obj));
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
    }
}
