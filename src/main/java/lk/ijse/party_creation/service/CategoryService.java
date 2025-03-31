package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.PartyCategoryDTO;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    int saveCategory(PartyCategoryDTO partyCategoryDTO);
    int updateCategory(PartyCategoryDTO partyCategoryDTO);
}
