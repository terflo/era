package com.era.courseapi.model.services;

import com.era.courseapi.exceptions.TopicNotFoundException;
import com.era.courseapi.model.entities.Course;
import com.era.courseapi.model.entities.Topic;
import com.era.courseapi.model.repositories.TopicRepository;
import com.era.courseapi.model.requests.NewTopicRequest;
import com.era.courseapi.model.services.interfaces.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public Topic saveTopic(Topic topic) throws TopicNotFoundException {

        if (this.topicRepository.existsById(topic.getId()))
            throw new TopicNotFoundException(String.format("Тема с ID: %s уже существует", topic.getId()));
        else if (this.topicRepository.existsByName(topic.getName()))
            throw new TopicNotFoundException(String.format("Тема с именем: %s уже существует", topic.getName()));

        return this.topicRepository.save(topic);
    }

    @Override
    public Topic saveNewTopic(Course course, NewTopicRequest newTopicRequest) throws TopicNotFoundException {
        if (this.topicRepository.existsByName(newTopicRequest.getName()))
            throw new TopicNotFoundException(String.format("Тема с именем: %s уже существует", newTopicRequest.getName()));

        return this.topicRepository
                .save(
                        Topic.builder()
                                .name(newTopicRequest.getName())
                                .description(newTopicRequest.getDescription())
                                .course(course)
                                .timestamp(new Date())
                                .difficultyRate(newTopicRequest.getDifficultRate())
                                .build()
                );
    }

    @Override
    public Topic getTopic(Long id) throws TopicNotFoundException {
        return this.topicRepository
                .getTopicById(id)
                .orElseThrow(
                        () -> new TopicNotFoundException(String.format("Тема с ID: %s не существует", id))
                );
    }

    @Override
    public Topic getTopic(String name) throws TopicNotFoundException {
        return this.topicRepository
                .getTopicByName(name)
                .orElseThrow(
                        () -> new TopicNotFoundException(String.format("Тема с именем: %s не существует", name))
                );
    }

    @Override
    public List<Topic> getAll() {
        return this.topicRepository.findAll();
    }

    @Override
    public void deleteTopic(Topic topic) throws TopicNotFoundException {
        this.deleteTopic(topic.getId());
    }

    @Override
    public void deleteTopic(Long id) throws TopicNotFoundException {
        if(!this.topicRepository.existsById(id))
            throw new TopicNotFoundException(String.format("Темы с ID: %s не существует", id));

        this.topicRepository.deleteById(id);
    }

    @Override
    public void deleteTopic(String name) throws TopicNotFoundException {
        if(!this.topicRepository.existsByName(name))
            throw new TopicNotFoundException(String.format("Темы с именем: %s не существует", name));

        this.topicRepository.existsByName(name);
    }
}
