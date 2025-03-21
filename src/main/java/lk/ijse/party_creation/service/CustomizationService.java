package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.CustomizationDTO;

import java.util.List;

public interface CustomizationService {
    CustomizationDTO getCustomization(String email);
    int saveCustomization(CustomizationDTO customization);
    List<CustomizationDTO> getAllCustomizations();
    int deleteCustomization(String email);
}
