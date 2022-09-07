package com.era.apicourse;

import com.era.apicourse.model.entities.Course;
import com.era.apicourse.model.requests.NewCourseRequest;
import com.era.apicourse.model.services.interfaces.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseControllerTest {

    private final static String CONTROLLER_URI = "/api/course";
    private final static String TEST_COURSE_NAME = "Test course name MockMVC";
    private final static String TEST_COURSE_DESCRIPTION = "Test course description MockMVC";
    private final static String NEW_TEST_COURSE_NAME = "Test course name MockMVC updated";
    private final static String NEW_TEST_COURSE_DESCRIPTION = "Test course description MockMVC updated";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper ;

    @Test
    @Order(1)
    public void shouldMvc() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    @Order(2)
    public void courseControllerTestPost() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post(CONTROLLER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(
                                        new NewCourseRequest(TEST_COURSE_NAME, TEST_COURSE_DESCRIPTION)
                                )))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(TEST_COURSE_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(TEST_COURSE_DESCRIPTION))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    public void courseControllerTestGet() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(CONTROLLER_URI)
                        .param("name", TEST_COURSE_NAME))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.not(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(TEST_COURSE_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(TEST_COURSE_DESCRIPTION))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(4)
    public void courseControllerTestPut() throws Exception {
        Course course = courseService.getCourseByName(TEST_COURSE_NAME);
        course.setName(NEW_TEST_COURSE_NAME);
        course.setDescription(NEW_TEST_COURSE_DESCRIPTION);

        mockMvc
                .perform(MockMvcRequestBuilders.put(CONTROLLER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(NEW_TEST_COURSE_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(NEW_TEST_COURSE_DESCRIPTION))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(5)
    public void courseControllerTestDelete() throws Exception {
        String uuid = courseService.getCourseByName(NEW_TEST_COURSE_NAME).getUuid();
        mockMvc
                .perform(MockMvcRequestBuilders.delete(CONTROLLER_URI)
                        .param("uuid", uuid))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc
                .perform(MockMvcRequestBuilders.get(CONTROLLER_URI)
                        .param("uuid", uuid))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
