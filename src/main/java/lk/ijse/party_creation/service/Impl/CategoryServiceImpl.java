package lk.ijse.party_creation.service.Impl;

import lk.ijse.party_creation.dto.PartyCategoryDTO;
import lk.ijse.party_creation.entity.PartyCategory;
import lk.ijse.party_creation.repo.CategoryRepo;
import lk.ijse.party_creation.service.CategoryService;
import lk.ijse.party_creation.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveCategory(PartyCategoryDTO partyCategoryDTO) {
        if(categoryRepo.existsById(partyCategoryDTO.getPartyCategoryID())){
            throw new RuntimeException("category already exists");
        }
        categoryRepo.save(modelMapper.map(partyCategoryDTO, PartyCategory.class));
        return VarList.Created;
    }

    @Override
    public int updateCategory(PartyCategoryDTO partyCategoryDTO) {
        try{
            if(categoryRepo.existsById(partyCategoryDTO.getPartyCategoryID())){
                PartyCategory partyCategory = categoryRepo.findById(partyCategoryDTO.getPartyCategoryID())
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
}
