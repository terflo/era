package com.era.apicourse.model.services;

import com.era.apicourse.exceptions.CourseAlreadyExistsException;
import com.era.apicourse.exceptions.CourseNotFoundException;
import com.era.apicourse.model.entities.Course;
import com.era.apicourse.model.repositories.CourseRepository;
import com.era.apicourse.model.requests.NewCourseRequest;
import com.era.apicourse.model.services.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public Course addNewCourse(NewCourseRequest newCourseRequest) throws CourseAlreadyExistsException {

        if(this.courseRepository.existsByName(newCourseRequest.getName()))
            throw new CourseAlreadyExistsException(
                    String.format("Курс с именем %s уже существует", newCourseRequest.getName())
            );

        return this.courseRepository.save(Course
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
    @Transactional
    public Course addCourse(Course course) throws CourseAlreadyExistsException {

        if (this.courseRepository.existsByUuid(course.getUuid()))
            throw new CourseAlreadyExistsException(
                    String.format("Курс с UUID %s уже существует", course.getUuid())
            );
        else if (this.courseRepository.existsByName(course.getName()))
            throw new CourseAlreadyExistsException(
                    String.format("Курс с именем %s уже существует", course.getName())
            );
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course updateCourse(Course course) throws CourseNotFoundException, CourseAlreadyExistsException {

        Course oldCourse = courseRepository
                .getCourseByUuid(course.getUuid())
                .orElseThrow(() -> {
                    throw new CourseNotFoundException(
                            String.format("Курс с UUID %s не существует", course.getUuid())
                    );
                });

        if(oldCourse.getName().equals(course.getName())) {
            throw new CourseAlreadyExistsException(
                    String.format("Курс с именем %s уже существует", course.getName())
            );
        }
        return this.courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Course course) {

        if (!this.courseRepository.existsByUuid(course.getUuid()))
            throw new CourseNotFoundException(
                    String.format("Курс с UUID %s не существует", course.getUuid())
            );
        this.courseRepository.delete(course);
    }

    @Override
    @Transactional
    public void deleteCourseByUuid(String uuid) {
        if (!this.courseRepository.existsByUuid(uuid))
            throw new CourseNotFoundException(
                    String.format("Курс с UUID %s не существует", uuid)
            );
        this.courseRepository.deleteById(uuid);
    }

    @Override
    public Course getCourseByUuid(String uuid) throws CourseNotFoundException {
        return this.courseRepository
                .getCourseByUuid(uuid)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format("Курс с UUID: %s не существует", uuid)
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

    //TODO: Поменять вывод на Pagination
    @Override
    public List<Course> getAll() {
        return this.courseRepository.findAll();
    }
}
