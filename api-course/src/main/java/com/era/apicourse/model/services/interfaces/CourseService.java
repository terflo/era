package com.era.apicourse.model.services.interfaces;

import com.era.apicourse.exceptions.CourseAlreadyExistsException;
import com.era.apicourse.exceptions.CourseNotFoundException;
import com.era.apicourse.model.entities.Course;
import com.era.apicourse.model.requests.NewCourseRequest;

import java.util.List;

public interface CourseService {

    Course addNewCourse(NewCourseRequest newCourseRequest) throws CourseAlreadyExistsException;

    Course addCourse(Course course) throws CourseAlreadyExistsException;

    Course updateCourse(Course course) throws CourseNotFoundException, CourseAlreadyExistsException;

    void deleteCourse(Course course) throws CourseNotFoundException;

    void deleteCourseByUuid(String uuid) throws CourseNotFoundException;

    Course getCourseByUuid(String uuid) throws CourseNotFoundException;

    Course getCourseByName(String name) throws CourseNotFoundException;

    Course getCourseByNameAndByUuid(String name, String uuid) throws CourseNotFoundException;

    List<Course> getAll();

}
