package com.era.apicourse.model.repositories;

import com.era.apicourse.model.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    Optional<Exam> getExamById(Long id);

    Optional<Exam> getExamByName(String name);

    Optional<Exam> getExamByIdAndName(Long id, String name);

    boolean existsByName(String name);

}
