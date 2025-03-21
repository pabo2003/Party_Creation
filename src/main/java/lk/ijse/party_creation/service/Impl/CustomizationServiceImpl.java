package lk.ijse.party_creation.service.Impl;

import lk.ijse.party_creation.dto.CustomizationDTO;
import lk.ijse.party_creation.entity.Customization;
import lk.ijse.party_creation.repo.CustomizationRepo;
import lk.ijse.party_creation.repo.UserRepo;
import lk.ijse.party_creation.service.CustomizationService;
import lk.ijse.party_creation.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomizationServiceImpl implements CustomizationService {
    @Autowired
    private CustomizationRepo customizationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Override
    public CustomizationDTO getCustomization(String email) {
        if (customizationRepo.existsByEmail(email)) {
            Customization customization = customizationRepo.findByEmail(email);
            return modelMapper.map(customization, CustomizationDTO.class);
        }else {
            return null;  // Return null if customization doesn't exist'
        }
    }

    @Override
    public int saveCustomization(CustomizationDTO customization) {
        if (customizationRepo.existsByEmail(customization.getEmail())){
            return VarList.Not_Acceptable;
        }else {
            Customization customization1 = modelMapper.map(customization, Customization.class);
            customizationRepo.save(customization1);
            return VarList.Created;
        }
    }

    @Override
    public List<CustomizationDTO> getAllCustomizations() {
        List<Customization> customizationDTOS = customizationRepo.findAll();
        return modelMapper.map(customizationDTOS , new TypeToken<List<CustomizationDTO>>(){}.getType());
    }

    @Override
    public int deleteCustomization(String email) {
        if (customizationRepo.existsByEmail(email)){
            customizationRepo.deleteByEmail(email);
            return VarList.OK;
        }else {
            return VarList.Not_Found;
        }
    }
}
