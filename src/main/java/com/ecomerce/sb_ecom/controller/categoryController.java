package com.ecomerce.sb_ecom.controller;
import com.ecomerce.sb_ecom.models.Category;
import com.ecomerce.sb_ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api") // adds /api to all the api request made
public class categoryController {

//    @Autowired
//    private CategoryService categoryService;

//     OR

    private CategoryService categoryService;
    public categoryController(CategoryService categoryService) { // dependency injection constructor
        this.categoryService = categoryService;
    }// finds a bean matching with CategoryService and automatically injects it

    @GetMapping("/public/categories")
    //or
    //@RequestMapping(value="/public/categories" ,method=RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories=categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        categoryService.createNewCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category added successfully");
    }

    @DeleteMapping("/adimin/deleteCategory/{CategoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long CategoryId) {
        try{
            String response=categoryService.deleteCategory(CategoryId);
            //return new ResponseEntity<>(response, HttpStatus.OK);
            //return ResponseEntity.ok(response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    @PutMapping("/public/updateCategory/{CategoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long CategoryId,@RequestBody Category category){
        try {
            String response = categoryService.updateCategory(CategoryId, category);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getReason());
        }
    }

}
