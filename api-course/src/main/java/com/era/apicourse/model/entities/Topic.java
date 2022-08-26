package com.era.courseapi.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Course course;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 1024)
    private String description;

    @Column(nullable = false, updatable = false)
    private Date timestamp;

    @OneToMany(mappedBy = "topic")
    List<Test> tests;

    @Column(nullable = false)
    private Integer difficultyRate;

}
