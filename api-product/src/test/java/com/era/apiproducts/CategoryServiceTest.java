package com.era.apiproducts;

import com.era.apiproducts.model.entities.Category;
import com.era.apiproducts.model.exceptions.CategoryNotFoundException;
import com.era.apiproducts.model.services.interfaces.CategoryService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles({"dev"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @Order(0)
    public void instanceExistenceTest() {
        Assertions.assertNotNull(categoryService);
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveCategoryTest() {

        Category category = Category
                .builder()
                .id(null)
                .name("Тестовая категория")
                .build();

        categoryService.saveCategory(category);
    }

    @Test
    @Order(2)
    public void readCategoryTest() {
        Category category = categoryService.getCategoryByName("Тестовая категория");
        Assertions.assertNotNull(category);
        Assertions.assertEquals("Тестовая категория", category.getName());
    }

    @Test
    @Order(3)
    public void readAllCategoryTest() {
        List<Category> categories = categoryService.getAll(Pageable.unpaged()).toList();
        Assertions.assertFalse(categories.isEmpty());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest() {
        Category category = categoryService.getCategoryByName("Тестовая категория");
        Assertions.assertNotNull(category);
        category.setName("Тестовая категория 2");
        Category categoryUpdated =  categoryService.updateCategory(category);
        Assertions.assertNotNull(categoryUpdated);
        Assertions.assertEquals(categoryUpdated.getName(), "Тестовая категория 2");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){

        Assertions.assertTrue(categoryService.existsCategoryByName("Тестовая категория 2"));
        Category category = categoryService.getCategoryByName("Тестовая категория 2");
        Assertions.assertNotNull(category);
        categoryService.deleteCategory(category);

        Assertions.assertFalse(categoryService.existsCategoryByName("Тестовая категория"));
        Assertions.assertFalse(categoryService.existsCategoryByName("Тестовая категория 2"));

        Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryByName("Тестовая категория"));
        Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryByName("Тестовая категория 2"));
    }
}
