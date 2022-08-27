package com.era.apicourse.model.repositories;

import com.era.apicourse.model.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    Optional<Course> getCourseByUuid(String uuid);

    Optional<Course> getCourseByName(String name);

    Optional<Course> getCourseByNameAndUuid(String name, String uuid);

    boolean existsByUuid(String uuid);

    boolean existsByName(String name);

}
