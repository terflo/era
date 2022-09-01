package com.era.apicourse.controllers;

import com.era.apicourse.model.entities.Topic;
import com.era.apicourse.model.requests.NewTopicRequest;
import com.era.apicourse.model.services.interfaces.CourseService;
import com.era.apicourse.model.services.interfaces.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topic")
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
