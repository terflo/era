package com.era.apicourse;

import com.era.apicourse.exceptions.CourseNotFoundException;
import com.era.apicourse.model.entities.Course;
import com.era.apicourse.model.requests.NewCourseRequest;
import com.era.apicourse.model.services.interfaces.CourseService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    private static final String TEST_COURSE_NAME = "Test course";
    private static final String TEST_COURSE_DESCRIPTION = "Test description for test course";
    private static final String NEW_TEST_COURSE_NAME = "Test course updated";
    private static final String NEW_TEST_COURSE_DESCRIPTION = "Test description for test course updated";

    @Test
    @Order(1)
    public void shouldService() {
        Assertions.assertNotNull(courseService);
    }

    @Test
    @Order(2)
    public void courseServiceTestCreate() {

        courseService.addNewCourse(
                NewCourseRequest
                        .builder()
                        .name(TEST_COURSE_NAME)
                        .description(TEST_COURSE_DESCRIPTION)
                        .build()
        );

        Assertions.assertEquals(courseService.getCourseByName(TEST_COURSE_NAME).getDescription(), TEST_COURSE_DESCRIPTION);
    }

    @Test
    @Order(3)
    public void courseServiceTestUpdate() {

        Course course = courseService.getCourseByName(TEST_COURSE_NAME);

        Assertions.assertEquals(course.getName(), TEST_COURSE_NAME);
        Assertions.assertEquals(course.getDescription(), TEST_COURSE_DESCRIPTION);

        course.setName(NEW_TEST_COURSE_NAME);
        course.setDescription(NEW_TEST_COURSE_DESCRIPTION);

        course = courseService.updateCourse(course);    //update course info
        Assertions.assertEquals(course.getName(), NEW_TEST_COURSE_NAME);
        Assertions.assertEquals(course.getDescription(), NEW_TEST_COURSE_DESCRIPTION);

        course = courseService.getCourseByName(NEW_TEST_COURSE_NAME);
        Assertions.assertEquals(course.getName(), NEW_TEST_COURSE_NAME);
        Assertions.assertEquals(course.getDescription(), NEW_TEST_COURSE_DESCRIPTION);

        Assertions.assertThrowsExactly(CourseNotFoundException.class,
                () -> courseService.getCourseByName(TEST_COURSE_NAME));
    }

    @Test
    @Order(4)
    public void courseServiceDelete() {
        Course course = courseService.getCourseByName(NEW_TEST_COURSE_NAME);
        courseService.deleteCourse(course);

        Assertions.assertThrowsExactly(CourseNotFoundException.class,
                () -> courseService.getCourseByName(NEW_TEST_COURSE_NAME));
    }

}
