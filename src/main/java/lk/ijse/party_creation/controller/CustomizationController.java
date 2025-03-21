package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.CustomizationDTO;
import lk.ijse.party_creation.dto.ResponseDTO;
import lk.ijse.party_creation.service.CustomizationService;
import lk.ijse.party_creation.util.VarList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/customizations")
@CrossOrigin
public class CustomizationController {
    private final CustomizationService customizationService;

    public CustomizationController(CustomizationService customizationService) {
        this.customizationService = customizationService;
    }

    @GetMapping(value = "/getMemberInfo")
    public ResponseEntity<ResponseDTO> getMemberByUserId(@RequestParam String email){
        CustomizationDTO customizationDTO = customizationService.getCustomization(email);

        if (customizationDTO == null){
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Can't find customer",null));
        }else{
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Success",customizationDTO));
        }
    }

    @PostMapping(value = "/saveCustomerInfo")
    public ResponseEntity<ResponseDTO> saveMemberInfo(@RequestBody CustomizationDTO customizationDTO){

        try{
            Date localDate = java.sql.Date.valueOf(LocalDate.now());
            customizationDTO.setDate(localDate);
            int res = customizationService.saveCustomization(customizationDTO);

            switch (res){
                case VarList.Created:
                    return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Customer information saved successfully",null));
                case VarList.Not_Acceptable:
                    return ResponseEntity.ok(new ResponseDTO(VarList.Not_Acceptable,"Email already exists",null));
                default:
                    return ResponseEntity.ok(new ResponseDTO(VarList.INTERNAL_SERVER_ERROR,"An error occurred",null));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
