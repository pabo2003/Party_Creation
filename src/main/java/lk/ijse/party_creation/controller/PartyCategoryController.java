package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.PartyCategoryDTO;
import lk.ijse.party_creation.dto.ResponseDTO;
import lk.ijse.party_creation.entity.PartyCategory;
import lk.ijse.party_creation.service.CategoryService;
import lk.ijse.party_creation.util.ResponseUtil;
import lk.ijse.party_creation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/category")
public class PartyCategoryController {

    @Autowired
    private final CategoryService categoryService;

    public PartyCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveProduct(@RequestBody PartyCategoryDTO partyCategoryDTO){
        System.out.println(partyCategoryDTO.getPartyCategoryID());
        System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddd"+partyCategoryDTO.getName());
        try{
            int res = categoryService.saveCategory(partyCategoryDTO);
            switch (res){
                case VarList.Created:
                    System.out.println("category saved successfully");
                    return ResponseEntity.ok(new ResponseDTO(VarList.Created, "category saved successfully", partyCategoryDTO));
                default:
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.Internal_Server_Error,"Failed to save category",null));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateProduct(@RequestBody PartyCategoryDTO partyCategoryDTO) {
        System.out.println("id: " + partyCategoryDTO.getPartyCategoryID());

        try{
            int res = categoryService.updateCategory(partyCategoryDTO);
            switch (res){
                case VarList.OK:
                    System.out.println("category updated successfully");
                    return ResponseEntity.ok(new ResponseDTO(VarList.OK, "category updated successfully", partyCategoryDTO));
                case VarList.Not_Found:
                    System.out.println("category not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                           .body(new ResponseDTO(VarList.Not_Found, "category not found", null));
                default:
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body(new ResponseDTO(VarList.Internal_Server_Error,"Failed to update category",null));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
