package com.arantes.oiltrack.services;

import com.arantes.oiltrack.dto.sale.SaleRequestDTO;
import com.arantes.oiltrack.dto.sale.SaleResponseDTO;
import com.arantes.oiltrack.dto.saleItem.SaleItemRequestDTO;
import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.Product;
import com.arantes.oiltrack.models.Sale;
import com.arantes.oiltrack.models.SaleItem;
import com.arantes.oiltrack.repositories.ProductRepository;
import com.arantes.oiltrack.repositories.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private ProductRepository productRepository;

    public List<SaleResponseDTO> findAll() {
        return repository.findAll().stream().map(SaleResponseDTO::new).toList();
    }

    public SaleResponseDTO findById(Long id) {
        return repository.findById(id).map(SaleResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found by id: " + id));
    }

    public SaleResponseDTO insert(SaleRequestDTO data) {
        Sale sale = new Sale(data);
        addSaleItem(sale, data);
        Sale savedSale = repository.save(sale);
        return new SaleResponseDTO(savedSale);
    }

    private void addSaleItem(Sale sale, SaleRequestDTO saleDTO) {
        for(SaleItemRequestDTO itemDTO : saleDTO.items()) {
            Product product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found by id: " + itemDTO.productId()));

            SaleItem saleItem = new SaleItem(sale, product, itemDTO.quantity(), itemDTO.price());
            sale.getItems().add(saleItem);
        }
    }

    public SaleResponseDTO update(Long id, SaleRequestDTO saleDTO) {
        try {
            Sale sale = repository.getReferenceById(id);
            updateData(sale, saleDTO);

            sale.getItems().clear();
            addSaleItem(sale, saleDTO);

            return new SaleResponseDTO(repository.save(sale));
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Sale not found by id: " + id);
        }
    }

    private void updateData(Sale sale, SaleRequestDTO saleDTO) {
        sale.setDateSale(saleDTO.dateSale());
        sale.setDescription(saleDTO.description());
        sale.setSaleStatus(saleDTO.saleStatus());
        sale.setObservation(saleDTO.observation());
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Sale not found by id: " + id);
        repository.deleteById(id);
    }
}
