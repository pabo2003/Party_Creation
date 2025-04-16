package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    int saveCategory(CategoryDTO partyCategoryDTO);
    int updateCategory(CategoryDTO partyCategoryDTO);
    List<CategoryDTO> getAllCategories();
    int deleteCategory(int categoryId);
}
