package com.era.apicourse.model.services;

import com.era.apicourse.exceptions.CourseAlreadyExistsException;
import com.era.apicourse.exceptions.CourseNotFoundException;
import com.era.apicourse.model.entities.Course;
import com.era.apicourse.model.repositories.CourseRepository;
import com.era.apicourse.model.requests.NewCourseRequest;
import com.era.apicourse.model.services.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course addNewCourse(NewCourseRequest newCourseRequest) throws CourseNotFoundException {
        return courseRepository.save(Course
                .builder()
                .uuid(UUID.randomUUID().toString())
                .name(newCourseRequest.getName())
                .description(newCourseRequest.getDescription())
                .timestamp(new Date())
                .visible(false)
                .exam(null)
                .topics(null)
                .build());
    }

    @Override
    public Course addCourse(Course course) throws CourseAlreadyExistsException {

        if (this.courseRepository.existsByUuid(course.getUuid()))
            throw new CourseAlreadyExistsException("Курс с таким UUID уже существует");
        else if (this.courseRepository.existsByName(course.getName()))
            throw new CourseAlreadyExistsException("Курс с таким именем уже существует");

        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {

        if (!this.courseRepository.existsByUuid(course.getUuid()))
            throw new CourseNotFoundException("Курс с таким UUID не существует");
        else if (this.courseRepository.existsByName(course.getName()))
            throw new CourseAlreadyExistsException("Курс с таким именем уже существует");

        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Course course) {

        if (!this.courseRepository.existsByUuid(course.getUuid()))
            throw new CourseNotFoundException("Курс с таким UUID не существует");

        this.courseRepository.delete(course);

    }

    @Override
    public void deleteCourseByUuid(String uuid) {
        if (!this.courseRepository.existsByUuid(uuid))
            throw new CourseNotFoundException("Курс с таким UUID не существует");

        this.courseRepository.deleteById(uuid);
    }

    @Override
    public Course getCourseByUuid(String uuid) throws CourseNotFoundException {
        return this.courseRepository
                .getCourseByUuid(uuid)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format("Курса с UUID: %s не существует", uuid)
                ));
    }

    @Override
    public Course getCourseByName(String name) throws CourseNotFoundException {
        return this.courseRepository
                .getCourseByName(name)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format("Курса с именем: %s не существует", name)
                ));
    }

    @Override
    public Course getCourseByNameAndByUuid(String name, String uuid) throws CourseNotFoundException {
        return this.courseRepository
                .getCourseByNameAndUuid(name, uuid)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format("Курса с именем: %s и UUID: %s не существует", name, uuid)
                ));
    }

    @Override
    public List<Course> getAll() {
        return this.courseRepository.findAll();
    }
}
