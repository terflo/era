package com.era.apicourse.controllers;

import com.era.apicourse.model.entities.Course;
import com.era.apicourse.model.requests.NewCourseRequest;
import com.era.apicourse.model.services.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @RolesAllowed({"Moderator"})
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String uuid,
            @RequestParam(required = false) String name) {

        if(StringUtils.isBlank(uuid) && StringUtils.isBlank(name))
            return ResponseEntity.ok(courseService.getAll());
        else if(!StringUtils.isBlank(uuid) && !StringUtils.isBlank(name))
            return ResponseEntity.ok(courseService.getCourseByNameAndByUuid(name, uuid));
        else if(StringUtils.isBlank(uuid))
            return ResponseEntity.ok(courseService.getCourseByName(name));
        return ResponseEntity.ok(courseService.getCourseByUuid(uuid));
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody NewCourseRequest newCourseRequest) {
        return ResponseEntity.ok(courseService.addNewCourse(newCourseRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCourse(@RequestParam String uuid) {
        this.courseService.deleteCourseByUuid(uuid);
        return ResponseEntity.ok().build();
    }
}
