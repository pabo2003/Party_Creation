package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.CustomizationDTO;
import lk.ijse.party_creation.entity.Customization;

import java.util.List;

public interface CustomizationService {
    CustomizationDTO getCustomizationByEmail(String email);
    int saveCustomization(CustomizationDTO customization);
    List<Customization> getAllCustomizations();
    int deleteCustomization(String email);
    int updateCustomization(CustomizationDTO customization);
}
