package lk.ijse.party_creation.service.Impl;

import lk.ijse.party_creation.dto.CategoryDTO;
import lk.ijse.party_creation.entity.Category;
import lk.ijse.party_creation.repo.CategoryRepo;
import lk.ijse.party_creation.service.CategoryService;
import lk.ijse.party_creation.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveCategory(CategoryDTO partyCategoryDTO) {
        if(categoryRepo.existsById(partyCategoryDTO.getPartyCategoryID())){
            throw new RuntimeException("category already exists");
        }
        categoryRepo.save(modelMapper.map(partyCategoryDTO, Category.class));
        return VarList.Created;
    }

    @Override
    public int updateCategory(CategoryDTO partyCategoryDTO) {
        try{
            if(categoryRepo.existsById(partyCategoryDTO.getPartyCategoryID())){
                Category partyCategory = categoryRepo.findById(partyCategoryDTO.getPartyCategoryID())
                       .orElseThrow(() -> new RuntimeException("category not found"));
                partyCategory.setName(partyCategoryDTO.getName());
                categoryRepo.save(partyCategory);
                return VarList.OK;
            }else {
                return VarList.Not_Found;
            }
        }catch (Exception e) {
            throw new RuntimeException("Failed to update category: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
    }

    @Override
    public int deleteCategory(int categoryId) {
        if (!categoryRepo.existsById(categoryId)){
            return VarList.Not_Found;
        }else {
            categoryRepo.deleteById(categoryId);
            return VarList.OK;
        }
    }
}
