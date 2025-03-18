package com.ecomerce.sb_ecom.service;

import com.ecomerce.sb_ecom.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

//- If `CategoryServiceImpl` implements `CategoryService`, Spring registers the bean under the
//        **interface name** (`CategoryService`) by default.

@Service  // declare it as a bean so that object can be created by spring ioc automatically
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categories=new ArrayList<>();
    private long nextId = 1L;
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }
    @Override
    public void createNewCategory(Category category) {
        category.setId(nextId++);
        categories.add(category);
    }
    @Override
    public String deleteCategory(Long categoryId) {
        Category category=categories.stream()
                .filter(c->c.getId()==(categoryId))
                .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
        categories.remove(category);
        return "Category of categoryId "+categoryId+" deleted successfully";
    }
    @Override
    public String updateCategory(Long categoryId,Category category) {
        Category existingCategory=categories.stream()
                .filter(c->c.getId()==(categoryId))
                .findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found to Update"));
        existingCategory.setName(category.getName());
        return "Category of categoryId "+categoryId+" updated successfully";
    }

}
