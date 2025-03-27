package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.CustomizationDTO;
import lk.ijse.party_creation.dto.ProductDTO;
import lk.ijse.party_creation.dto.ResponseDTO;
import lk.ijse.party_creation.entity.Customization;
import lk.ijse.party_creation.service.CustomizationService;
import lk.ijse.party_creation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customizations")
@CrossOrigin("*")
public class CustomizationController {
    private final CustomizationService customizationService;

    public CustomizationController(CustomizationService customizationService) {
        this.customizationService = customizationService;
    }

    @GetMapping(value = "/getMemberInfo")
    public ResponseEntity<ResponseDTO> getMemberByUserId(@RequestParam String email){
        CustomizationDTO customizationDTO = customizationService.getCustomizationByEmail(email);

        if (customizationDTO == null){
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Can't find customer",null));
        }else{
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Success",customizationDTO));
        }
    }

    @PostMapping(value = "/saveCustomerInfo")
    public ResponseEntity<ResponseDTO> saveCustomerInfo(@RequestBody CustomizationDTO customizationDTO){
        System.out.println(customizationDTO);
        CustomizationDTO customizationDTO1 = customizationDTO;
        customizationDTO1.setNICNumber(customizationDTO.getNICNumber());
        customizationDTO1.setEmail(customizationDTO.getEmail());
        customizationDTO1.setPhone(customizationDTO.getPhone());
        customizationDTO1.setAddress(customizationDTO.getAddress());
        customizationDTO1.setUserDTO(customizationDTO.getUserDTO());
        try {
            Date localDate = Date.valueOf(LocalDate.now());
            customizationDTO1.setDate(localDate);
            int res = customizationService.saveCustomization(customizationDTO1);
            switch (res){
                case VarList.Created:
                    System.out.println("customer saved");
                    return ResponseEntity.ok(new ResponseDTO(VarList.Created, "customer Saved Successfully", customizationDTO1));
                case VarList.Not_Found:
                    System.out.println("customer not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponseDTO(VarList.Not_Found, "customer Not Found", null));
                default:
                    System.out.println("Error saving customer");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.Internal_Server_Error, "Error saving customer", null));
            }
        }catch (Exception e){
            System.out.println("Exception occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Internal Server Error", null));
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateCustomization(@RequestBody CustomizationDTO customizationDTO) {
        System.out.println("id: " + customizationDTO.getId());

        try{
            int res = customizationService.updateCustomization(customizationDTO);

            switch (res) {
                case VarList.OK:
                    System.out.println("customer updated");
                    return ResponseEntity.ok(new ResponseDTO(VarList.OK, "customer Updated Successfully", null));
                case VarList.Not_Found:
                    System.out.println("customer not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponseDTO(VarList.Not_Found, "customer Not Found", null));
                default:
                    System.out.println("Error updating customer");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.Internal_Server_Error, "Error updating customer", null));
            }
        }catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Internal Server Error", null));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO> deleteCustomization(@PathVariable int customId) {
        int productId = customizationService.deleteCustomization(String.valueOf(customId));
        ResponseDTO response = new ResponseDTO(200, "customer delete successfully", productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getAll")
    public ResponseEntity<ResponseDTO> getAllCustomers() {
        List<Customization> products = customizationService.getAllCustomizations();
        ResponseDTO response = new ResponseDTO(200, "All customer fetched successfully", products);
        return ResponseEntity.ok(response);
    }
}
