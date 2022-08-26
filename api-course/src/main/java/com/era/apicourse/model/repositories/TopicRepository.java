package com.era.courseapi.model.repositories;

import com.era.courseapi.model.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsById(Long id);

    boolean existsByName(String name);

    Optional<Topic> getTopicById(Long id);

    Optional<Topic> getTopicByName(String name);

    void deleteByName(String name);

}
