package com.arantes.oiltrack.services;

import com.arantes.oiltrack.dto.category.CategoryRequestDTO;
import com.arantes.oiltrack.dto.category.CategoryResponseDTO;

import com.arantes.oiltrack.exceptions.custom.ResourceNotFoundException;
import com.arantes.oiltrack.models.Category;
import com.arantes.oiltrack.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository CategoryRepository;

    public List<CategoryResponseDTO> findAll() {
        return CategoryRepository.findAll().stream().map(CategoryResponseDTO::new).toList();

    }

    public CategoryResponseDTO findById(Long id) {
        return CategoryRepository.findById(id).map(CategoryResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found by id: " + id));
    }

    public CategoryResponseDTO insert(CategoryRequestDTO data) {
        Category savedCategory = CategoryRepository.save(new Category(data));
        return new CategoryResponseDTO(savedCategory);
    }

    public void delete(Long id) {
        if (!CategoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found by id: " + id);
        }
        CategoryRepository.deleteById(id);
    }

    public CategoryResponseDTO update(Long id, CategoryRequestDTO categoryDTO) {
        try {
            Category obj = CategoryRepository.getReferenceById(id);
            updateData(obj, categoryDTO);
            return new CategoryResponseDTO(CategoryRepository.save(obj));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Category not found by id: " + id);
        }
    }

    private void updateData(Category entity, CategoryRequestDTO receivedCategoryDTO) {
        entity.setName(receivedCategoryDTO.name());
    }
}
