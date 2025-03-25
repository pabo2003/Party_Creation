package lk.ijse.party_creation.service.Impl;

import lk.ijse.party_creation.dto.CustomizationDTO;
import lk.ijse.party_creation.entity.Customization;
import lk.ijse.party_creation.entity.User;
import lk.ijse.party_creation.repo.CustomizationRepo;
import lk.ijse.party_creation.repo.UserRepo;
import lk.ijse.party_creation.service.CustomizationService;
import lk.ijse.party_creation.util.VarList;
import org.modelmapper.ModelMapper;
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
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CustomizationDTO getCustomizationByEmail(String email) {
        Customization customization = customizationRepo.findByEmail(email);
        return modelMapper.map(customization, CustomizationDTO.class);
    }

    @Override
    public int saveCustomization(CustomizationDTO customization) {
       if (customizationRepo.existsByEmail(customization.getEmail())) {
           return VarList.Not_Acceptable;
       }else {
           Customization customization1 = modelMapper.map(customization , Customization.class);
           customizationRepo.save(customization1);
           return VarList.Created;
       }
    }

    @Override
    public List<Customization> getAllCustomizations() {
        List<Customization> result = customizationRepo.findAll();
        return result.stream().map(customization->modelMapper.map(customization, Customization.class)).toList();
    }

    @Override
    public int deleteCustomization(String email) {
        if(customizationRepo.existsByEmail(email)){
            customizationRepo.deleteByEmail(email);
            return VarList.OK;
        }else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int updateCustomization(CustomizationDTO customization) {
        /*try {
            Customization customization1 = customizationRepo.findByEmail(customization.getEmail());
            customization1.setName(customization.getName());
            customization1.setAddress(customization.getAddress());
            customization1.setPhone(customization.getPhone());
            customization1.setNICNumber(customization.getNICNumber());

            User user = userRepo.findByEmail(customization.getEmail());
            customization1.setUser(user);

            customizationRepo.save(customization1);
            return VarList.OK;
        }catch (Exception e){
            throw new RuntimeException("Failed to update customer: "+e.getMessage());
        }*/
        return 0;
    }
}
