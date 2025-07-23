package com.arantes.oiltrack.services;

import com.arantes.oiltrack.dto.product.ProductRequestDTO;
import com.arantes.oiltrack.dto.product.ProductResponseDTO;
import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.Category;
import com.arantes.oiltrack.models.Product;
import com.arantes.oiltrack.models.ProductPrice;
import com.arantes.oiltrack.repositories.CategoryRepository;
import com.arantes.oiltrack.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductResponseDTO> findAll() {
        return repository.findAll().stream().map(ProductResponseDTO::new).toList();
    }

    public ProductResponseDTO findById(Long id) {
        return repository.findById(id).map(ProductResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found by id: " + id));
    }

    public ProductResponseDTO insert(ProductRequestDTO data) {
        Category category = categoryRepository.findById(data.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found by id: " + data.categoryId()));

        Product product = repository.save(new Product(data, category));
        return new ProductResponseDTO(product);
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Product not found by id: " + id);
        repository.deleteById(id);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO productDTO) {
        try {
            Product product = repository.getReferenceById(id);
            UpdateData(product, productDTO);
            return new ProductResponseDTO(repository.save(product));
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Product not found by id: " + id);
        }
    }

    private void UpdateData(Product product, ProductRequestDTO productDTO) {
        product.setName(productDTO.name());

        Category category = categoryRepository.findById(productDTO.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found by id: " + productDTO.categoryId()));
        product.setCategory(category);

        product.setDescription(productDTO.description());
        product.getPrices().clear();

        List<ProductPrice> updatedPrices = productDTO.prices().stream()
                .map(priceDTO -> new ProductPrice(priceDTO, product)).toList();

        product.getPrices().addAll(updatedPrices);
    }
}
