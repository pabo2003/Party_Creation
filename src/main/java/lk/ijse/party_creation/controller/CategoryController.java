package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.CategoryDTO;
import lk.ijse.party_creation.dto.ResponseDTO;
import lk.ijse.party_creation.service.CategoryService;
import lk.ijse.party_creation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveProduct(@RequestBody CategoryDTO partyCategoryDTO){
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
    public ResponseEntity<ResponseDTO> updateProduct(@RequestBody CategoryDTO partyCategoryDTO) {
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

    @GetMapping("getAll")
    public ResponseEntity<ResponseDTO> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        ResponseDTO response = new ResponseDTO(200, "All products fetched successfully", categories);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable int categoryId) {
        int deletedId = categoryService.deleteCategory(categoryId);
        ResponseDTO response = new ResponseDTO(200, "Category deleted successfully", deletedId);
        return ResponseEntity.ok(response);
    }

}
