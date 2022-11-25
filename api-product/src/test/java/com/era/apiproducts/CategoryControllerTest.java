package com.era.apiproducts;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"dev"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryControllerTest {
}
