package com.era.courseapi.model.services.interfaces;

import com.era.courseapi.exceptions.TopicNotFoundException;
import com.era.courseapi.model.entities.Course;
import com.era.courseapi.model.entities.Topic;
import com.era.courseapi.model.requests.NewTopicRequest;

import java.util.List;

public interface TopicService {

    Topic saveTopic(Topic topic) throws TopicNotFoundException;

    Topic saveNewTopic(Course course, NewTopicRequest newTopicRequest) throws TopicNotFoundException;

    Topic getTopic(Long id) throws TopicNotFoundException;

    Topic getTopic(String name) throws TopicNotFoundException;

    List<Topic> getAll();

    void deleteTopic(Topic topic) throws TopicNotFoundException;

    void deleteTopic(Long id) throws TopicNotFoundException;

    void deleteTopic(String name) throws TopicNotFoundException;
}
