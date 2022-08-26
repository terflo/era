package com.era.courseapi.controllers;

import com.era.courseapi.model.entities.Course;
import com.era.courseapi.model.entities.Topic;
import com.era.courseapi.model.requests.NewTopicRequest;
import com.era.courseapi.model.services.interfaces.CourseService;
import com.era.courseapi.model.services.interfaces.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getTopic(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name) {

        if(id == null && (name == null || name.isEmpty()))
            return ResponseEntity.ok(this.topicService.getAll());
        else if(id == null)
            return ResponseEntity.ok(this.topicService.getTopic(name));
        else
            return ResponseEntity.ok(this.topicService.getTopic(id));

    }

    @PostMapping
    public ResponseEntity<Topic> saveTopic(@RequestBody NewTopicRequest newTopicRequest) {

        return ResponseEntity.ok(
                this.topicService.saveNewTopic(
                        this.courseService.getCourseByName(newTopicRequest.getCourseName()), newTopicRequest
                ));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTopic(@RequestParam Long id) {

        this.topicService.deleteTopic(id);

        return ResponseEntity.ok().build();

    }

}
