package com.era.courseapi.model.services.interfaces;

import com.era.courseapi.exceptions.CourseAlreadyExistsException;
import com.era.courseapi.exceptions.CourseNotFoundException;
import com.era.courseapi.model.entities.Course;
import com.era.courseapi.model.requests.NewCourseRequest;

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
