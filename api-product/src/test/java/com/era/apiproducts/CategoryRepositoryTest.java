package com.era.apiproducts;

import com.era.apiproducts.model.entities.Category;
import com.era.apiproducts.model.repositories.CategoryRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles({"dev"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Order(0)
    public void instanceExistenceTest() {
        Assertions.assertNotNull(categoryRepository);
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

        categoryRepository.save(category);
    }

    @Test
    @Order(2)
    public void readCategoryTest() {
        Category category = categoryRepository.getCategoryByName("Тестовая категория").orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("Тестовая категория", category.getName());
    }

    @Test
    @Order(3)
    public void readAllCategoryTest() {
        List<Category> categories = categoryRepository.findAll();
        Assertions.assertFalse(categories.isEmpty());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest() {
        Category category = categoryRepository.getCategoryByName("Тестовая категория").orElse(null);
        Assertions.assertNotNull(category);
        category.setName("Тестовая категория 2");
        Category categoryUpdated =  categoryRepository.save(category);
        Assertions.assertNotNull(categoryUpdated);
        Assertions.assertEquals(categoryUpdated.getName(), "Тестовая категория 2");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){

        Assertions.assertTrue(categoryRepository.existsCategoryByName("Тестовая категория 2"));
        Category category = categoryRepository.getCategoryByName("Тестовая категория 2").orElse(null);
        Assertions.assertNotNull(category);
        categoryRepository.delete(category);

        Assertions.assertFalse(categoryRepository.existsCategoryByName("Тестовая категория"));
        Assertions.assertFalse(categoryRepository.existsCategoryByName("Тестовая категория 2"));

        Assertions.assertFalse(categoryRepository.getCategoryByName("Тестовая категория").isPresent());
        Assertions.assertFalse(categoryRepository.getCategoryByName("Тестовая категория 2").isPresent());
    }

}
